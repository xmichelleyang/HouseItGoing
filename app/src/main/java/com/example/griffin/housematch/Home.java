package com.example.griffin.housematch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Home extends AppCompatActivity {

    // Data
    ArrayList<House> houses;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Read user data
        try {
            FileInputStream reader = openFileInput("user_temp");
            ObjectInputStream objReader = new ObjectInputStream(reader);
            user = (User) objReader.readObject();
            System.out.println("Successfully read user_temp!");
            System.out.println("The maximum price is " + user.max_price);
        } catch (IOException e) {
            System.out.println("Fatal error: could not read user_temp!");
        } catch (ClassNotFoundException e) {
            System.out.println("Fatal error: could not find the class!");
        }

        // Read house data
        String line;
        houses = new ArrayList<House>();
        try {
            FileInputStream reader = openFileInput("housing_data.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(reader);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split(",");
                String address = fields[0];
                float totalBedrooms = Float.parseFloat(fields[1]);
                float openBedrooms = Float.parseFloat(fields[2]);
                float bathrooms = Float.parseFloat(fields[3]);
                float distance = Float.parseFloat(fields[4]);
                float price = Float.parseFloat(fields[5]);
                int sqft = Integer.parseInt(fields[6]);
                String email = fields[7];
                House house = new House(address, totalBedrooms, openBedrooms, bathrooms,
                        distance, price, sqft, email);
                houses.add(house);
                System.out.println("Successfully read house!");
                System.out.println("The email is " + house.email);
            }
            ;
        } catch (IOException e) {
            System.out.println("Fatal error: could not read housing_data.csv!");
        }

        for (int i=0;  i < houses.size(); i++) {
            House house = houses.get(i);
            float ranking = user.rank(house);
            house.rank = ranking;
        }
        Collections.sort(houses, new HouseComparator());

        double price = 0;
        double[] weight = {513.03918, -63.222137, 512.29199, 513.7132, -0.0014636014, 511.87949};
        float[] features = {1,user.distance, user.open_bedrooms, user.bathrooms,
            user.sqft, 1};
        for (int j=0; j < features.length; j++) {
            price += weight[j]*features[j];
        }

        TextView house1 = (TextView)findViewById(R.id.textView11);
        TextView house2 = (TextView)findViewById(R.id.textView12);
        TextView house3 = (TextView)findViewById(R.id.textView13);
        TextView house4 = (TextView)findViewById(R.id.textView14);
        TextView house5 = (TextView)findViewById(R.id.textView15);

        String first_house = "1) " + houses.get(0).address + String.valueOf(houses.get(0).distance) + " " +
                String.valueOf(houses.get(0).open_bedrooms) + " " +
                String.valueOf(houses.get(0).bathrooms) + " " +
                String.valueOf(houses.get(0).sqft) + " " +
                String.valueOf(houses.get(0).max_price) + " " +
                        houses.get(0).email;

        String second_house = "2) " + houses.get(1).address + String.valueOf(houses.get(0).distance) + " " +
                String.valueOf(houses.get(1).open_bedrooms) + " " +
                String.valueOf(houses.get(1).bathrooms) + " " +
                String.valueOf(houses.get(1).sqft) + " " +
                String.valueOf(houses.get(1).max_price) + " " +
                houses.get(1).email;

        String third_house = "3) " + houses.get(2).address + String.valueOf(houses.get(0).distance) + " " +
                String.valueOf(houses.get(2).open_bedrooms) + " " +
                String.valueOf(houses.get(2).bathrooms) + " " +
                String.valueOf(houses.get(2).sqft) + " " +
                String.valueOf(houses.get(2).max_price) + " " +
                houses.get(2).email;

        String fourth_house = "4) " + houses.get(3).address + String.valueOf(houses.get(0).distance) + " " +
                String.valueOf(houses.get(3).open_bedrooms) + " " +
                String.valueOf(houses.get(3).bathrooms) + " " +
                String.valueOf(houses.get(3).sqft) + " " +
                String.valueOf(houses.get(3).max_price) + " " +
                houses.get(3).email;

        String fifth_house = "5) " + houses.get(4).address + String.valueOf(houses.get(0).distance) + " " +
                String.valueOf(houses.get(4).open_bedrooms) + " " +
                String.valueOf(houses.get(4).bathrooms) + " " +
                String.valueOf(houses.get(4).sqft) + " " +
                String.valueOf(houses.get(4).max_price) + " " +
                houses.get(4).email;
        try{
            house1.setText(first_house);
            house2.setText(second_house);
            house3.setText(third_house);
            house4.setText(fourth_house);
            house5.setText(fifth_house);
        } catch (NullPointerException e) {
            System.out.println("zzzz");
        }

        TextView model = (TextView)findViewById(R.id.textView10);
        model.setText("Based on our model and your preferences, the estimated price fair price is $"
            + String.valueOf(price));
    }

}
