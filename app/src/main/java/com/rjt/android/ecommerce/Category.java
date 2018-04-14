package com.rjt.android.ecommerce;

public class Category {
    private String cid;
    private String cname;
    private String cdiscription;
    private String cimegrl;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdiscription() {
        return cdiscription;
    }

    public void setCdiscription(String cdiscription) {
        this.cdiscription = cdiscription;
    }

    public String getCimegrl() {
        return cimegrl;
    }

    public void setCimegrl(String cimegrl) {
        this.cimegrl = cimegrl;
    }

    public Category(String cid, String cname, String cdiscription, String cimegrl) {
        this.cid = cid;
        this.cname = cname;
        this.cdiscription = cdiscription;
        this.cimegrl = cimegrl;
    }

    public String toString(){
        return "cid" + this.cid + "/" +this.cname + "/" + this.cdiscription + "/" + this.cimegrl;
    }


}
