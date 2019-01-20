package com.example.vincent.bardroid;

import java.util.List;

public class Data {
    private List<Table> tables;
    private List<Bartender> bartenders;
    private List<Consumable> consumables;
    //transferType is a variable that is passed to make the server/client aware which kind of data the object Data contains.
    //1 = Backup (all data of client/server is passed in the data object.)
    //2 = New Start of system (when all the data has been wiped out.)
    //      -> Contains Table[], Bartender[], consumable[].
    //3 = Specific data (Table objects)
    private Integer transferType;
    private Integer dataVersion;
    public Data(Integer transferType, List<Table> tables, List<Bartender> bartenders, List<Consumable> consumables, Integer dataVersion)
    {
        this.transferType = transferType;
        this.tables = tables;
        this.bartenders = bartenders;
        this.consumables = consumables;
        this.dataVersion = dataVersion;
    }
    public Data(Integer transferType,List<Table> tables,Integer dataVersion)
    {
        this.transferType = transferType;
        this.tables = tables;
        this.dataVersion = dataVersion;
    }
    public Integer getTransferType()
    {
        return this.transferType;
    }
}
