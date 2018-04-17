package com.rjt.android.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.rjt.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        PublicUtility pub = new PublicUtility();
        SharedPreferences preferences = getSharedPreferences("ecommerce", MODE_PRIVATE);

            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    /* Create an Intent that will start the Menu-Activity. */
                    Intent intent = new Intent(SplashActivity.this, PrepareActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();

                }
            }, 5000);
        }


    }
