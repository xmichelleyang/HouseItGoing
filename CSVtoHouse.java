import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex;

import com.opencsv.CSVReader;

public class CSVtoHouse {

   public static void main(String[] args) {
      //create CSVReader object
      try {
         CSVReader reader = new CSVReader(new FileReader("cleaned_housing.csv"), ',');


         List <House> houses = new ArrayList<House>();

         //Read line by line
         String[] record = null;
         try {
            while ((record = reader.readNext()) != null) {
               try {
                  String address = record[0];
                  int total_bedrooms = Integer.parseInt(record[1]);
                  int open_bedrooms = Integer.parseInt(record[2]);
                  int bathrooms = Integer.parseInt(record[3]);
                  float distance = Float.parseFloat(record[4]);
                  float max_price = Float.parseFloat(record[5]);
                  int sqft = Integer.parseInt(record[6]);

                  //if add parsing was done without error, create and add house
                  House house = new House(address, total_bedrooms, open_bedrooms,
                                          bathrooms, distance, max_price, sqft);
                  houses.add(house);
               }
               catch (NumberFormatException e) {
                  System.err.println("Invalid integer or float value inputted");
               }
               catch (NullPointerException e1) {
                  System.err.println("String for parseFloat is null");
               }

            }
            reader.close();

         }
         catch (IOException e) {
            System.err.println("IOException");
         }
         System.out.println("hello" + houses);

      }
      catch (FileNotFoundException e2) {
         System.err.println("File not found");
      }
   }
}
