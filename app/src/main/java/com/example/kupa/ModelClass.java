package com.example.kupa;

public class ModelClass {

    private int imagefood, textcount, textamount,sausecount,sauseamount,toppingcount,toppingamount,totalcart,delete;
    private String textname;


    public ModelClass(int imagefood, String textname, int textcount, int textamount,int sausecount,int sauseamount,int toppingcount,int toppingamount,int totalcart,int delete){

        this.imagefood=imagefood;
        this.textname=textname;
        this.textcount=textcount;
        this.textamount=textamount;
        this.sausecount=sausecount;
        this.sauseamount=sauseamount;
        this.toppingcount=toppingcount;
        this.toppingamount=toppingamount;
        this.totalcart=totalcart;
        this.delete=delete;

    }


    public int getImagefood() {
        return imagefood;
    }

    public int getTextcount() {
        return textcount;
    }

    public int getTextamount() {
        return textamount;
    }

    public int getSausecount() {
        return sausecount;
    }

    public int getSauseamount() {
        return sauseamount;
    }

    public int getToppingcount() {
        return toppingcount;
    }

    public int getToppingamount() {
        return toppingamount;
    }

    public String getTextname() {
        return textname;
    }

    public int getTotalcart() {
        return totalcart;
    }

    public int getDelete() {
        return delete;
    }
}

