package com.example.griffin.housematch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TenantPref extends Activity {
    EditText totalroomrank, openroomrank, bathroomrank, pricerank, distancerank, sqftrank;
    EditText total_bedrooms, open_bedrooms, bathrooms, price, distance, sqft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_pref);
    }

    public void toMainScreen(View view) {
        Intent intent = new Intent(TenantPref.this, MainScreen.class);
        startActivity(intent);
    }

    public void toHomeScreen(View view) {
        totalroomrank = (EditText) findViewById(R.id.totalroomrank);
        openroomrank = (EditText) findViewById(R.id.openroomrank);
        pricerank = (EditText) findViewById(R.id.pricerank);
        distancerank = (EditText) findViewById(R.id.distancerank);
        sqftrank = (EditText) findViewById(R.id.sqftrank);
        bathroomrank = (EditText) findViewById(R.id.bathroomrank);


        int[] priorities = {Integer.parseInt(totalroomrank.getText().toString()),
                Integer.parseInt(openroomrank.getText().toString()), Integer.parseInt(pricerank.getText().toString()),
                Integer.parseInt(distancerank.getText().toString()), Integer.parseInt(sqftrank.getText().toString()),
                Integer.parseInt(bathroomrank.getText().toString())};



        total_bedrooms = (EditText) findViewById(R.id.totalroomrank);
        open_bedrooms = (EditText) findViewById(R.id.openroomrank);
        bathrooms = (EditText) findViewById(R.id.pricerank);
        price = (EditText) findViewById(R.id.distancerank);
        distance = (EditText) findViewById(R.id.sqftrank);
        sqft = (EditText) findViewById(R.id.bathroomrank);

        User user = new User(
                Float.parseFloat(total_bedrooms.getText().toString()),
                Float.parseFloat(open_bedrooms.getText().toString()), Float.parseFloat(bathrooms.getText().toString()),
                Float.parseFloat(price.getText().toString()), Float.parseFloat(distance.getText().toString()),
                Integer.parseInt(sqft.getText().toString()),
                priorities
        );

        // Save user data as a temporary file
        String filename = "user_temp";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream writer = new ObjectOutputStream(outputStream);
            writer.writeObject(user);
            writer.close();
            System.out.println("Temporarily saved user data.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(TenantPref.this, Home.class);
        startActivity(intent);

    }

}
