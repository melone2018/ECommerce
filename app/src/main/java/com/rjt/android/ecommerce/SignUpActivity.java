package com.rjt.android.ecommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rjt.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mMobileNum;
    private EditText mAddress;
    private EditText mEmail;
    private EditText mPassword;
    private Button mSignUp;
    private RequestQueue mQueue;
    private Button mLoginAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirstName = findViewById(R.id.EditTextFirstName);
        mLastName = findViewById(R.id.EditTextLastName);
        mMobileNum = findViewById(R.id.EditTextMobile);
        mAddress = findViewById(R.id.EditTextAddress);
        mEmail = findViewById(R.id.EditTextEmail);
        mPassword = findViewById(R.id.EditTextPassword);
        mSignUp = findViewById(R.id.ButtonSignUp);
        mLoginAccount = findViewById(R.id.ButtonRetriveAccount);
        mQueue = Volley.newRequestQueue(this);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = mFirstName.getText().toString();
                String lName = mLastName.getText().toString();
                String mobile = mMobileNum.getText().toString();
                String address = mAddress.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(fName.isEmpty() || lName.isEmpty() || mobile.isEmpty()
                        || address.isEmpty() || email.isEmpty() || password.isEmpty()){
                    nontifyInformationIncomplete("Please provide all information");
                }else{
                    registerCustomer(fName, lName, mobile, address, email, password);
                }

            }
        });

        mLoginAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
            }
        });
    }

    public void nontifyInformationIncomplete(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void registerCustomer(final String firstName, final String lastName, final String mobile, final String address, final String email, final String password)
    {
        Log.d("SEND CUSTOMER", firstName + " "+lastName + " "+mobile);
        boolean requestRes = false;
        String url = PublicUtility.getInstance().getRegistrationSite();
        Log.d("URL:", url);
        StringRequest strreq = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        if(Response.contains("successfully")){
                            Log.d("Response", Response);
                            nontifyInformationIncomplete("You Successfully Registered Your account!");
                            Intent intent = new Intent(SignUpActivity.this, MenuActivity.class);
                            startActivity(intent);
                        }
                        if(Response.contains("Mobile number already exsist")){
                            nontifyInformationIncomplete("This phone number has been used, please change to another one.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.e("HttpClient", "error: " + e.toString());
                //return false;
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fname", firstName);
                params.put("lname", lastName);
                params.put("mobile", mobile);
                params.put("address", address);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        mQueue.add(strreq);
    }
}
