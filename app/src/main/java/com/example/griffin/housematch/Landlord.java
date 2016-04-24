package com.example.griffin.housematch;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class Landlord extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landlord);
    }
    public void toMainScreen(View view) {
        Intent intent = new Intent(Landlord.this, MainScreen.class);
        startActivity(intent);

    }

}
