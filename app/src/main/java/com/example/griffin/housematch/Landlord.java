package com.example.griffin.housematch;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class Landlord extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord);

        String address = ((EditText)findViewById(R.id.editText)).getText().toString();
        String total_bedrooms = ((EditText)findViewById(R.id.editText2)).getText().toString();
        String open_bedrooms = ((EditText)findViewById(R.id.editText3)).getText().toString();
        String bathrooms = ((EditText)findViewById(R.id.editText4)).getText().toString();
        String distance = ((EditText)findViewById(R.id.editText5)).getText().toString();
        String sqft = ((EditText)findViewById(R.id.editText6)).getText().toString();
        String price = ((EditText)findViewById(R.id.totalroomrank)).getText().toString();
        String email = ((EditText)findViewById(R.id.openroomrank)).getText().toString();

        House house = new House(address, Float.parseFloat(total_bedrooms),
                Float.parseFloat(open_bedrooms), Float.parseFloat(bathrooms),
                Float.parseFloat(distance), Float.parseFloat(price),
                Integer.parseInt(sqft), email);

        try{house.save();}
        catch(IOException e) {
            System.out.println("Cannot find file.");
        }

    }
    public void toMainScreen(View view) {
        Intent intent = new Intent(Landlord.this, MainScreen.class);
        startActivity(intent);

    }

}
