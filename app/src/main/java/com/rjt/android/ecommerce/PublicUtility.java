package com.rjt.android.ecommerce;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;

public class PublicUtility {
    private static PublicUtility sPublicUtility;
    private static String REGISTRATION_SITE;
    private static String LOGINSITE;

    public static String getSharedFile() {
        return SharedFile;
    }

    private static String SharedFile;

    public static void setmSharedPreferenes(SharedPreferences mSharedPreferenes) {
        PublicUtility.mSharedPreferenes = mSharedPreferenes;
    }

    private static SharedPreferences mSharedPreferenes;
    public static String getRegistrationSite() {
        return REGISTRATION_SITE;
    }

    public static String getMobileNumberUsed() {
        return MOBILE_NUMBER_USED;
    }

    private static String MOBILE_NUMBER_USED;
    public static String getLOGINSITE() {
        return LOGINSITE;
    }

    public PublicUtility(){

    }
    public static void saveLoginData(JsonObject jsonObject){
        String name = jsonObject.getAsString("name");
    }
    public static PublicUtility getInstance(){
        if(sPublicUtility==null){
            SharedFile = "Ecommerce";
            MOBILE_NUMBER_USED = "Mobile number not register";
            REGISTRATION_SITE = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?";
            LOGINSITE = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_login.php?";
            sPublicUtility =  new PublicUtility();

        }
        return sPublicUtility;
    }
}
