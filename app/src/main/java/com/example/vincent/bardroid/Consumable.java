package com.example.vincent.bardroid;

import java.util.Locale;

public class Consumable {
    private Integer alcoholPercentage,orderId;
    private Double price;
    private String name,category,type;

    public Consumable(String name, Double price)
    {
        this.name = name;
        this.price = price;
    }
    public Double getPrice()
    {
        return price;
    }
    public Integer getOrderid(){return orderId;}
    public String getName(){return name;}
}
