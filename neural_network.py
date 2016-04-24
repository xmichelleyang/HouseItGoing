"""
Uses the TensorFlow neural network to make predictions about apartment price
given feature vectors.

Usage:
    neural_network.py --train <source>

Options:
    --help -h   Show this screen.
    --train -t  Train the neural network from dataset stored at SOURCE.
"""


from docopt import docopt
from random import shuffle
from re import search
from time import time
import csv
import numpy as np
import tensorflow as tf


"""
Utility method for printing out the list of apartments.
"""
def print_apartments(apartments):
    for apartment in apartments:
        print(apartment)


"""
Reads from CSV file and returns a list of apartments and the list of cities.
"""
def read_from_csv(filename):
    apartments = []
    cities = set()
    try:
        with open(filename, 'r') as f:
            data = csv.reader(f)
            for row in data:
                try:
                    # print(row)

                    # Regular expression parsing from uncleaned dataset
                    city = search('(\w+)\sCA', row[0]).group(1)
                    cities.add(city)
                    distance = float(search('(.+)\sMiles', row[1]).group(1))
                    if (row[2] == 'Studio'):
                        bedrooms = 0.5
                    else:
                        bedrooms = float(search('(.+)\sBedroom', row[2]).group(1))
                    bathrooms = float(search('(.+)\sBathroom', row[3]).group(1))
                    sqft = int(search('(.+)\ssq.\sft', row[4]).group(1))
                    price = int(search('\$(\d+)', row[5]).group(1))

                    apartments.append((city, distance,  bedrooms, bathrooms, sqft, price))
                except AttributeError:
                    continue
    finally:
        f.close()
    return apartments, cities


"""
Writes the regex-parsed apartment data to a cleaned CSV file.
"""
def write_cleaned_csv(apartments, filename):

    # Convert tuples to lists and all fields to strings.
    apartments = [[str(row) for row in apartment] for apartment in apartments]

    try:
        with open(filename, 'w') as f:
            data = csv.writer(f, delimiter=',')
            data.writerows(apartments)
        print("Successfully wrote to CSV format! Congratulations!")
    except Exception as e:
        print("Oops, catastrophic failure! Oh no!")
        print(e)
    finally:
        f.close()


"""
Neural network class with associated methods.
"""
class ApartmentNeuralNetwork:

    """
    Initializes the neural network.
    """
    def __init__(self, dataset, cities):

        NUM_NUMERIC_FEATURES = 4
        self.cities = list(cities)
        self.num_features = len(cities) + NUM_NUMERIC_FEATURES + 1

        print("Initializing neural network with {:d} features...".format(self.num_features))
        self.sess = tf.Session()
        self.x = tf.placeholder(tf.float32, [None, self.num_features], name="input")
        self.weights = tf.Variable(tf.random_normal([self.num_features, 1]), name="weights")
        self.activation = tf.matmul(self.x, self.weights)
        self.y = tf.placeholder(tf.float32, [None, 1], name="output")

        print("Separating dataset into training set and hold-out set...")
        TRAINING_RATIO = 0.9
        data = self.transform_dataset(dataset)
        index = int(len(data) * 0.9)
        shuffle(data)
        self.training_set = data[:index]
        self.num_samples = len(self.training_set)
        self.hold_out_set = data[index:]

        # Uses L2 loss function
        print("Initializing loss function...")
        LEARNING_RATE = 0.01
        self.cost = tf.reduce_mean(tf.pow(tf.sub(self.activation, self.y), 2), name="loss")
        # self.optimizer = tf.train.GradientDescentOptimizer(LEARNING_RATE).minimize(self.cost)
        self.optimizer = tf.train.AdamOptimizer().minimize(self.cost)

        print("Initializing neurons...")
        self.init = tf.initialize_all_variables()

        print("Successfully initialized neural network!")

    """
    Converts each apartment data point into a feature vector.
    """
    def vectorize_apartment(self, apartment):
        city, dist, bedrooms, bathrooms, sqft, price = apartment
        features = [1.0 if c == city else 0.0 for c in self.cities]
        features.extend([dist, bedrooms, bathrooms, sqft, 1.0])
        return features, [price]

    """
    Transforms the dataset for TensorFlow compatibility.
    """
    def transform_dataset(self, dataset):
        self.features = list(self.cities)
        self.features.extend(['Distance', 'Bedrooms', 'Bathrooms', 'Square Feet', 'Bias'])
        return [self.vectorize_apartment(apartment) for apartment in apartments]

    """
    Learns the regression model using stochastic gradient descent.
    """
    def learn_model(self):

        print("Starting deep learning...")
        start_time = time()
        self.sess.run(self.init)
        DISPLAY_STEP = 1000

        train_X, train_Y = zip(*self.training_set)
        train_X = np.array(list(train_X))
        train_Y = np.array(list(train_Y))
        hold_out_X, hold_out_Y = zip(*self.hold_out_set)
        hold_out_X = np.array(list(hold_out_X))
        hold_out_Y = np.array(list(hold_out_Y))
            
        # NUM_EPOCHS = 20000
        # for i in range(NUM_EPOCHS):

        lowest_hold_out_loss = float('inf')
        counter = 0

        # Currently set to optimize hold-out data loss
        while True:

            try:

                # Displays progress
                if counter % DISPLAY_STEP == 0:
                    hold_out_loss = self.sess.run(self.cost, feed_dict={self.x: hold_out_X, self.y: hold_out_Y})
                    print("Processed {:d} samples, training loss is currently at {:.2f}.".format(counter, self.sess.run(self.cost, feed_dict={self.x: train_X, self.y: train_Y})))
                    if hold_out_loss > lowest_hold_out_loss:
                        print("Optimimum hold-out training efficiency reached! Terminating deep learning.")
                        break
                    else:
                        lowest_hold_out_loss = hold_out_loss
                        print("Hold-out loss decreased to {:.2f}.".format(hold_out_loss))

                self.sess.run(self.optimizer, feed_dict={self.x: train_X, self.y: train_Y})
                counter += 1

            except ValueError as e:

                import pdb; pdb.set_trace()

        print("Machine learning complete in {:.5f} seconds!".format(time() - start_time))
        self.learned_weight_vector = self.sess.run(self.weights)
        print("Learned weight vector: " + str([entry[0] for entry in self.learned_weight_vector]))
        print("Feature vector: " + str(self.features))


if __name__ == '__main__':
    arguments = docopt(__doc__)
    print("Loading in data...")
    apartments, cities = read_from_csv(arguments['<source>'])
    print("Found number of cities in dataset: {:d}".format(len(cities)))

    brain = ApartmentNeuralNetwork(apartments, cities)
    brain.learn_model()
