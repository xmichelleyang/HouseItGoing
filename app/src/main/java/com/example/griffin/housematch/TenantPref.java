package com.example.griffin.housematch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TenantPref extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant_pref);
    }

    public void toMainScreen(View view) {
        Intent intent = new Intent(TenantPref.this, MainScreen.class);
        startActivity(intent);
    }

    
}
