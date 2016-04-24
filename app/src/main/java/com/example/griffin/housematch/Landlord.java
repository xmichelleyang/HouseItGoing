package com.example.griffin.housematch;

import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;

public class Landlord extends Activity {

    EditText address, total_bedrooms, open_bedrooms, bathrooms, distance, sqft, price, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord);

        this.address = (EditText)findViewById(R.id.editText);
        this.total_bedrooms = (EditText)findViewById(R.id.editText2);
        this.open_bedrooms = (EditText)findViewById(R.id.editText3);
        this.bathrooms = (EditText)findViewById(R.id.editText4);
        this.distance = (EditText)findViewById(R.id.editText5);
        this.sqft = (EditText)findViewById(R.id.editText6);
        this.price = (EditText)findViewById(R.id.totalroomrank);
        this.email = (EditText)findViewById(R.id.openroomrank);

    }
    public void toMainScreen(View view) {
        String address = this.address.getText().toString();
        String total_bedrooms = this.total_bedrooms.getText().toString();
        String open_bedrooms = this.open_bedrooms.getText().toString();
        String bathrooms = this.bathrooms.getText().toString();
        String distance = this.distance.getText().toString();
        String sqft = this.sqft.getText().toString();
        String price = this.price.getText().toString();
        String email = this.email.getText().toString();

        House house = new House(address, Float.parseFloat(total_bedrooms),
                Float.parseFloat(open_bedrooms), Float.parseFloat(bathrooms),
                Float.parseFloat(distance), Float.parseFloat(price),
                Integer.parseInt(sqft), email);

        String filename = "housing_data.csv";
        FileOutputStream outputStream;
        String contents = house.save();

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(contents.getBytes());
            outputStream.close();
            System.out.println("Successfully saved housing data");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(Landlord.this, MainScreen.class);
        startActivity(intent);
    }

}
