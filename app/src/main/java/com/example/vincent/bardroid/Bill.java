package com.example.vincent.bardroid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bill {
    private Table table;
    public Bill(Table table)
    {
        this.table = table;
    }
    public String getBillContent()
    {
        List<Consumable> mergedConsumableList = new ArrayList<>();
        List<String> namesOfMergedDrinkList = new ArrayList<>();
        for (Order o : table.getOrderList())
            if(mergedConsumableList != null)
                mergedConsumableList.addAll(o.getConsumableList());
            else
                mergedConsumableList = o.getConsumableList();
        for (Consumable d : mergedConsumableList)
            namesOfMergedDrinkList.add(d.getName());
        return printCountedDuplicates(namesOfMergedDrinkList) + "\n"+ "Total: " + printConsumtionTotal(mergedConsumableList);
    }
    public String printCountedDuplicates(List<String> list)
    {
        String result = "";
        Set<String> unique = new HashSet<>(list);
        for (String key : unique) {
            result += Collections.frequency(list, key)+ "X  " + key+"\n";
        }
        return result;
    }
    public Double printConsumtionTotal(List<Consumable> list)
    {
        Double total = 0.0;
        for(Consumable d: list)
            total += d.getPrice();
        return total;
    }
}
