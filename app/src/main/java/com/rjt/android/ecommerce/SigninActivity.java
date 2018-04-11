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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
    EditText mMobileTx;
    EditText mPasswordTx;
    Button mLoginBtn;
    RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mMobileTx = findViewById(R.id.EditTextMobile);
        mPasswordTx = findViewById(R.id.EditTextPassword);
        mRequestQueue = Volley.newRequestQueue(this);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobileTx.getText().toString();
                String password = mPasswordTx.getText().toString();
                if(mobile.isEmpty() || password.isEmpty()){
                    Toast.makeText(SigninActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                }else{
                   loginCustomer(mobile, password);
                }
            }
        });
    }

    public void loginCustomer(final String mobile, final String password)
    {
        StringRequest strreq = new StringRequest(Request.Method.POST,
                PublicUtility.getInstance().getLOGINSITE(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // not registered
                        if(Response.contains(PublicUtility.getInstance().getMobileNumberUsed())){
                            Toast.makeText(SigninActivity.this, PublicUtility.getInstance().getMobileNumberUsed(), Toast.LENGTH_SHORT).show();
                        }else{  // mobile registered, forward to Menu Page

                            Intent intent = new Intent(SigninActivity.this, MenuActivity.class);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.e("HttpClient", "error: " + e.toString());
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("password", password);
                params.put("mobile", mobile);
                return params;
            }
        };
        mRequestQueue.add(strreq);
    }
}
