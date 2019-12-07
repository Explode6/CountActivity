package com.example.countactivity;

public class CountRecord {
    String name;
    String price;
    String time;
    public CountRecord(String name, String price,String time){
        this.name = name;
        this.price = price;
        this.time = time;
    }
    public String getName(){
        return name;
    }
    public String getTime(){return time;}
    public String getPrice(){
        return price;
    }
}
