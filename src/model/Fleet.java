package model;

import java.util.LinkedHashMap;

/**
 * Created by santa on 6/15/15.
 */
public class Fleet {


    private  static Automobile a1 = new Automobile();

    private static LinkedHashMap<String, Automobile> autoHash = new LinkedHashMap<String, Automobile>();

    public void setAutomobile(String model1, Automobile auto){
       autoHash.put(model1,auto);  // using this function to put into the hasmap of automobiles

    }


    public void printAutomobile(String model){
       a1=autoHash.get(model);
       a1.printauto();
    }

    public Automobile getAutomobile(String model){
        a1=autoHash.get(model);
        return a1;
    }



}
