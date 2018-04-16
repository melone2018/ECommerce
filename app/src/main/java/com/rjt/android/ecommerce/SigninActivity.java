package com.rjt.android.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.rjt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class SigninActivity extends AppCompatActivity {
    EditText mMobileTx;
    EditText mPasswordTx;
    Button mLoginBtn;
    String CURL;
    RequestQueue mRequestQueue;
    Button mButtonWantAccount;
    SharedPreferences pb;
    PublicUtility publicResource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        pb = getSharedPreferences("ecommerce", 0);
        CURL = "http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?";
        publicResource = new PublicUtility().getInstance();
        mMobileTx = findViewById(R.id.EditTextSignInPhone);
        mPasswordTx = findViewById(R.id.EditTextSignInPw);
        mRequestQueue = Volley.newRequestQueue(this);
        mLoginBtn = findViewById(R.id.ButtonSignIn);
        mButtonWantAccount = findViewById(R.id.ButtonWantAccount);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = mMobileTx.getText().toString();
                String password = mPasswordTx.getText().toString();
                if (mobile.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SigninActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                } else {
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

    public void loginCustomer(final String mobile, final String password) {
        //String input = PublicUtility.getLOGINSITE(mobile, password);
        String input = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?" + "mobile=" + mobile + "&password="+password;
        Log.d("URLLOGIN", input);
        JsonArrayRequest jreq = new JsonArrayRequest(
                input, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jo = response.getJSONObject(0);
                    String mobile = jo.getString("mobile");
                    String fname = jo.getString("firstname");
                    String lname = jo.getString("lastname");
                    String email = jo.getString("email");
                    String apiKey = jo.getString("appapikey ");
                    String id = jo.getString("id");
                    Log.d("JSON", mobile + " " + fname + " "+lname + " "+apiKey);
                    pb.edit().putString(id, password + " " + apiKey).apply();
                    requestInternetResources(id, apiKey);
                    //PublicUtility.getmSharedPreferenes().edit().putString(mobile, new Customer(fname, lname, email, mobile, apiKey, id).toString()).apply();
                    Intent intent = new Intent(SigninActivity.this, MenuActivity.class);
                    Bundle bundle = new Bundle();
                    String[] userInfo = new String[]{mobile, password, apiKey, id};
                    bundle.putStringArray("USERINFO", userInfo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("LOGIN HTTP ERROR", "ERROR!!!");
            }
        });
        mRequestQueue.add(jreq);
    }

    public void requestInternetResources(String id, String apiKey){
        final Map<String, ArrayList<String>> categoryMap = new HashMap<>();
        //RequestQueue requestQueue = Volley.newRequestQueue(SigninActivity.this);
        String categoryUrl = CURL + "api_key=" +apiKey + "&user_id=" + id;
        Log.d("Category", categoryUrl);
        //final List<Category> list = new ArrayList<>();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                categoryUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONArray jsonArray = response.getJSONArray("category");
                            Log.d("LENGTH", Integer.toString(jsonArray.length()));
                            ArrayList<String> images = new ArrayList<>();
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Iterator<?> keys = jsonObject.keys();
                                String cid = null;
                                String cname=null;
                                String cdiscription=null;
                                String cimage=null;
                                //String tmpImage;
                                while(keys.hasNext()){
                                    String key = (String)keys.next();
                                    String value = jsonObject.getString(key);
                                    if(key.equals("cid")){
                                        cid = value;
                                    }else if(key.equals("cname")){
                                        cname = value;
                                    }else if(key.equals("cdiscription")){
                                        cdiscription = value;
                                    }else{
                                        cimage = value;
                                    }
                                }

                                if(cid != null && cname!=null && cdiscription != null && cimage!=null) {
                                   images.add(cimage);
                                   pb.edit().putString("cid"+cid, cimage).apply();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("HTTP909", "Failed ");
            }
        });
        mRequestQueue.add(jsonObjReq);

//        Map<String, ?> allEntries = pb.getAll();
//        String s = pb.getString("cid107", null);
//        Log.d("CID107I", s);
    }

}
