package com.rjt.android.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rjt.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PublicUtility pUtility = PublicUtility.getInstance();
        startActivity(new Intent(SplashActivity.this, SignUpActivity.class));
        finish();
    }
}
