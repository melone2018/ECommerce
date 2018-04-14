package com.rjt.android.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class PublicUtility {
    private static PublicUtility sPublicUtility;
    private static String REGISTRATION_SITE;
    private static String LOGINSITE;
    private static String CATEGORYSITE;
    private static SharedPreferences mSharedPreferenes;
    private static List<Category> sCategories;
    private static String SharedFile;

    public static void setCategories(Category category) {
        sCategories.add(category);
    }

    public static List<Category> getCategories() {
        return sCategories;
    }

    public static SharedPreferences getmSharedPreferenes() {
        return mSharedPreferenes;
    }

    public static String getSharedFile() {
        return SharedFile;
    }

    public static void setmSharedPreferenes(SharedPreferences mSharedPreferenes) {
        PublicUtility.mSharedPreferenes = mSharedPreferenes;
    }

    public static String getRegistrationSite(String fname, String lname, String mobile, String address, String email, String password) {
        return REGISTRATION_SITE + "fname="+fname+"&lname="+lname+"&address="+address+"&email="+email+"&mobile="+mobile+"&password="+password;
    }

    public static String getCATEGORYSITE(String id, String apiKey){
        return CATEGORYSITE + "api_key="+apiKey + "&user_id="+id;
    }
    public static String getMobileNumberUsed() {
        return MOBILE_NUMBER_USED;
    }

    private static String MOBILE_NUMBER_USED;
    public static String getLOGINSITE(String mobile, String password) {
        return LOGINSITE+ "mobile=" + mobile + "&password="+password;
    }

    public PublicUtility(){

    }

    public static PublicUtility getInstance(){
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

    public static ArrayList<String> getImages(){
        ArrayList<String> images = new ArrayList<>();
        for(int i = 0; i < sCategories.size();i++){
            images.add(sCategories.get(i).getCimegrl());
        }
        return images;
    }
}
