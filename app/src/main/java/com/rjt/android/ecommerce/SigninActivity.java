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
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rjt.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
    EditText mMobileTx;
    EditText mPasswordTx;
    Button mLoginBtn;
    RequestQueue mRequestQueue;
    Button mButtonWantAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mMobileTx = findViewById(R.id.EditTextSignInPhone);
        mPasswordTx = findViewById(R.id.EditTextSignInPw);
        mRequestQueue = Volley.newRequestQueue(this);
        mLoginBtn = findViewById(R.id.ButtonSignIn);
        mButtonWantAccount = findViewById(R.id.ButtonWantAccount);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = mMobileTx;
                String mobile = mMobileTx.getText().toString();
                String password = mPasswordTx.getText().toString();
                if(mobile.isEmpty() || password.isEmpty()){
                    Toast.makeText(SigninActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                }else{
                   loginCustomer(mobile, password);
                }
            }
        });

        mButtonWantAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginCustomer(final String mobile, final String password)
    {

        Map<String, String> params = new HashMap<>();
        params.put("password", password);
        params.put("mobile", mobile);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(PublicUtility.getInstance().getLOGINSITE(),
                new JSONObject(params), new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Parsing json object response
                    String firstname = response.getString("fname");
                    String mobile = response.getString("mobile");
                    String lastname = response.getString("lastname");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });




        StringRequest strreq = new StringRequest(Request.Method.POST,
                PublicUtility.getInstance().getLOGINSITE(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        try {

                            // not registered
                            if (Response.contains(PublicUtility.getInstance().getMobileNumberUsed())) {
                                Toast.makeText(SigninActivity.this, PublicUtility.getInstance().getMobileNumberUsed(), Toast.LENGTH_SHORT).show();
                            } else {  // mobile registered, forward to Menu Page
                                Log.d("Response: ", Response);
                                JSONObject jsonObject = new JSONObject(Response);
                                Intent intent = new Intent(SigninActivity.this, MenuActivity.class);

                            }
                        }catch(JSONException e){
                            e.printStackTrace();
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
