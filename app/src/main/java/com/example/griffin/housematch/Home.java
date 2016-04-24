package com.example.griffin.housematch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
            };
        } catch (IOException e) {
            System.out.println("Fatal error: could not read housing_data.csv!");
        }

    }

}
