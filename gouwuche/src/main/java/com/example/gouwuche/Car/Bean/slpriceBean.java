package com.example.gouwuche.Car.Bean;

/**
 * author:Created by MingShao on 2018/4/5.
 */

public class slpriceBean {
    String price;
    int count;


    public slpriceBean(String price, int count) {
        this.price = price;
        this.count = count;
    }


    public String getPrice() {


        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }
}
