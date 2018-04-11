package com.rjt.android.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {
    EditText mMobileTx;
    EditText mPasswordTx;
    Button mLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mMobileTx = findViewById(R.id.EditTextMobile);
        mPasswordTx = findViewById(R.id.EditTextPassword);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobileTx.getText().toString();
                String password = mPasswordTx.getText().toString();
                if(mobile.isEmpty() || password.isEmpty()){
                    Toast.makeText(SigninActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(SigninActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
