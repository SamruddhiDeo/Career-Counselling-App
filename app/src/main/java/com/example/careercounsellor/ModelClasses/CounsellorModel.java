package com.example.careercounsellor.ModelClasses;

public class CounsellorModel {
    public int img;
    public String name, rating, price;
    public String date,time,typeOfCall;

    public CounsellorModel(int img, String name, String rating, String price) {
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.price = price;
    }

    public CounsellorModel() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypeOfCall() {
        return typeOfCall;
    }

    public void setTypeOfCall(String typeOfCall) {
        this.typeOfCall = typeOfCall;
    }

    public CounsellorModel(int img, String name, String rating, String price, String date, String time, String typeOfCall) {
        this.img = img;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.date = date;
        this.time = time;
        this.typeOfCall = typeOfCall;
    }
}
