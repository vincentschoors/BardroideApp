package com.example.vincent.bardroid;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Consumable> consumableList;
    private Bartender bartender;
    public Order(Bartender bartender)
    {
        this.bartender = bartender;
        consumableList = new ArrayList<Consumable>();
    }
    public void addDrink(Consumable consumable)
    {
        consumableList.add(consumable);
    }
    public void removeDrink(Integer orderId)
    {
        Integer count = 0;
        for(Consumable d : consumableList) {
            if (d.getOrderid() == orderId)
            {
                consumableList.remove(count);
            }
            count++;
        }
    }
    public List<Consumable> getConsumableList()
    {
        return this.consumableList;

    }
    public Double getSubtotal()
    {
        Double subtotal= 0.0;
        for(Consumable d : consumableList)
            subtotal=+d.getPrice();
        return subtotal;
    }
}
