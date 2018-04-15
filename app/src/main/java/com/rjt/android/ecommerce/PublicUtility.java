package com.rjt.android.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class PublicUtility {
    private static PublicUtility sPublicUtility;
    private String REGISTRATION_SITE;
    private String LOGINSITE;
    private String CATEGORYSITE;
    private SharedPreferences mSharedPreferenes;
    private List<Category> sCategories;
    private String SharedFile;
    private String MOBILE_NUMBER_USED;

    public PublicUtility(){

    }

    public PublicUtility getInstance(){
        if(sPublicUtility==null)
        {
            sCategories = new ArrayList<>();
            SharedFile = "Ecommerce";
            MOBILE_NUMBER_USED = "Mobile number not register";
            REGISTRATION_SITE = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?";
            LOGINSITE = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?";
            CATEGORYSITE="http://rjtmobile.com/ansari/shopingcart/androidapp/cust_category.php?";
            sPublicUtility =  new PublicUtility();

        }
        return sPublicUtility;
    }


    public void setCategories(Category category) {
        sCategories.add(category);
    }

    public List<Category> getCategories() {
        return sCategories;
    }

    public SharedPreferences getmSharedPreferenes() {
        return mSharedPreferenes;
    }

    public String getSharedFile() {
        return SharedFile;
    }

    public void setmSharedPreferenes(SharedPreferences mSharedPreferenes) {
        this.mSharedPreferenes = mSharedPreferenes;
    }

    public String getRegistrationSite(String fname, String lname, String mobile, String address, String email, String password) {
        return REGISTRATION_SITE + "fname="+fname+"&lname="+lname+"&address="+address+"&email="+email+"&mobile="+mobile+"&password="+password;
    }

    public String getCATEGORYSITE(String id, String apiKey){
        return CATEGORYSITE + "api_key="+apiKey + "&user_id="+id;
    }
    public String getMobileNumberUsed() {
        return MOBILE_NUMBER_USED;
    }

    public  String getLOGINSITE(String mobile, String password) {
        return LOGINSITE+ "mobile=" + mobile + "&password="+password;
    }


    public ArrayList<String> getImages(){
        ArrayList<String> images = new ArrayList<>();
        for(int i = 0; i < sCategories.size();i++){
            images.add(sCategories.get(i).getCimegrl());
        }
        return images;
    }
}
