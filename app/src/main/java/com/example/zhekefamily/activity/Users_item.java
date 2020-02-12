package com.example.zhekefamily.activity;

public class Users_item {
    private String name;
    private int imageID;
    public Users_item(String n, int imageID)
    {
        this.name=n;
        this.imageID=imageID;
    }
    public String getName()
    {
        return name;
    }
    public int getimageID()
    {
        return imageID;
    }
}
