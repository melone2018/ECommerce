package com.rjt.android.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rjt.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PublicUtility pub = new PublicUtility();
        //PublicUtility pUtility = PublicUtility.getInstance();
       // SharedPreferences sp = getSharedPreferences("ecommerce", MODE_PRIVATE);
        //pub.setmSharedPreferenes(sp);
        SharedPreferences preferences = getSharedPreferences("ecommerce", MODE_PRIVATE);
      //  preferences.edit().commit();
        startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
        finish();
    }
}
