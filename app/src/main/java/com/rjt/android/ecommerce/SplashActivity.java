package com.rjt.android.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rjt.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PublicUtility pUtility = PublicUtility.getInstance();
        SharedPreferences sp = getSharedPreferences(pUtility.getSharedFile(), MODE_PRIVATE);
        PublicUtility.setmSharedPreferenes(sp);
        startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
        finish();
    }
}
