package com.rjt.android.ecommerce;

public class PublicUtility {
    private static PublicUtility sPublicUtility;
    private static String REGISTRATION;
    public PublicUtility(){

    }
    public static PublicUtility getInstance(){
        if(sPublicUtility==null){
            REGISTRATION = "http://rjtmobile.com/aamir/e-commerce/android-app/shop_reg.php?";
            sPublicUtility =  new PublicUtility();
        }
        return sPublicUtility;
    }
    public String getREGISTRATION(){
        return REGISTRATION;
    }
}
