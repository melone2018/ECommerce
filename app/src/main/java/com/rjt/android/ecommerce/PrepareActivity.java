package com.rjt.android.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rjt.R;

public class PrepareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
    }

    public void onConfirm(View view) {
        Intent intent = new Intent(PrepareActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void onCancel(View view) {
        finish();
    }
}
