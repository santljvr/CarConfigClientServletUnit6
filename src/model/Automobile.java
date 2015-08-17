package model;

import java.io.*;
import java.util.ArrayList;
import model.OptionSet.Option;
import java.util.Scanner;
/*to setup Automobile and its properties *
 * Created by Santosh on 6/4/15.
 */
public class Automobile extends Exception implements Serializable{


    private static final long serialVersionUID = 42L;

    private String make = new String();  //changed autoname to make
    private float baseprice =0;
    private String model = new String();  //added model

    public ArrayList<OptionSet> opset = new ArrayList<OptionSet>(); //converted to ArrayList  
    public synchronized ArrayList<OptionSet> getOptionSets() {
		return opset;
	}
    
	public synchronized OptionSet getOptionSet(String name) {
		for(int i=0 ; i<opset.size(); i++)
		{
			if(opset.get(i).getOptionsetname().equals(name)) {
				return opset.get(i);
			}
		}
		return null;
	}

    
    public String getModel() {
        return model;
    }


    public String getMake() {
        return make;
    }

    public float getBaseprice() {
        return baseprice;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Automobile () {
        super();
    }

    public void setOptionSet(int index){
        for(int i=0; i<index; i++){
            OptionSet opt2 = new OptionSet();
            opset.add(i,opt2);
        }
    }

    public String getAutoname() {
        return make;
    }   //returning make

    public void setAutoname(String make) {
        this.make = make;
    }

    public float getAutoprice() {
        return baseprice;
    }  //returning base price

    public void setAutoprice(float baseprice) {
        this.baseprice = baseprice;
    }

    public OptionSet getOpset(int index) {
        return opset.get(index);
    } //returning optionset


    public void setOption(int index, int optionsize1) {
         opset.get(index).setOption2(optionsize1);
    }

    public void setOptionname(int index1, int index2, String oname) {

        opset.get(index1).setOptionName(index2, oname);
    }

    //below are methods for setting up the user choices and building the price

    public String getOptionchoice(String optionsetname){
        return findOptionSetbyName(optionsetname).choice.getName();
    }



    public void setOptionchoice(String optionsetname, String optionname){
        findOptionSetbyName(optionsetname).choice = findOptionSetbyName(optionsetname).findoption(optionname);
    }

    public float getOptionchoiceprice(String optionsetname){
        return findOptionSetbyName(optionsetname).choice.getPrice();
    }

    public float getTotalprice() { //this will build th price as suggested in the document
        float totalprice = baseprice;
        for(int i=0; i<opset.size();i++){
            totalprice = totalprice + getOptionchoiceprice(opset.get(i).getOptionsetname());
        }
        return totalprice;
    }

    public void setOpsetname(int index1, String setname) {
        opset.get(index1).setOptionsetname(setname);
    }

    public void setOptionprice(int index1, int index2, float price) {

        opset.get(index1).setOptionPrice(getOptionName(index1,index2), price);
    }

    public void Automobile (String model1, int size, String n, float s) {
    	
      for(int i=0; i<size; i++){
            OptionSet optionset1 = new OptionSet();
            opset.add(i,optionset1);
        }

         //to get the size of the option set
        make = n;
        baseprice = s;
        model = model1;
    }

    public String getOptionSetName(int index){
        String optionsetname1 = new String();
        optionsetname1 = opset.get(index).getOptionsetname();
        return optionsetname1;
    }



    public String getOptionName(int index, int index2){
        String optionname1 = new String();
        optionname1 = opset.get(index).getOptionName(index2);
            return  optionname1;
    }

    public float getOptionPrice(int index3, int index4){
        float optionprice2;
        optionprice2 = opset.get(index3).getOptionPrice(index4);
        return optionprice2;
    }

    public void setopsetname (int index, String opsetname){
        opset.get(index).setOptionsetname(opsetname);
    }


    public int getNumOption(int index) {

        return this.opset.get(index).getNumber();

    }

    public void setNumOption(int index,int cnt) {
        this.opset.get(index).setNumber(cnt);
    }

    //to find optionset by name and return index
    public int  findoptionset(String optionname){
        for(int i=0; i<opset.size(); i++){
            if(opset.get(i).getOptionsetname().equals(optionname)){
                return i;
            }
        }
        return -1;
    }

    //find optionset by name and return opset
    public OptionSet findOptionSetbyName(String optionsetname){
        for(int i=0; i<opset.size(); i++){
            if(opset.get(i).getOptionsetname().equals(optionsetname)){
                return opset.get(i);
            }
        }
        return null;
    }

    // to find option by optioname

    public Option findOption(String name) {
        for (int i = 0; i < opset.size(); i++) {
            opset.get(i).findoption(name);
        }
        return null;
    }

    //to delete an option
    public void deleteoptionset(int index){
        opset.get(index).setOptionsetname(null);
    }

    //update options with new set of options for any given option set
    public void updateoption(int index,String name){
        if(index<opset.size()) {
                opset.get(index).setOptionsetname(name);

            }
        }

    public void updateOptPrice(String optName, String opt, float price){

        for(int i =0; i<opset.size(); i++){
            if(opset.get(i).getOptionsetname().equals(optName))
                opset.get(i).updateOptPrice(opt, price);
        }

    }

    public void printauto() {

        System.out.println(" \n The make of the car is: " + this.make);
        System.out.println(" \n The name of the model is: " + this.model);
        System.out.println("\n The base price of the model is: " + this.baseprice);

        for (int i = 0; i < opset.size(); i++) {
            opset.get(i).print();
            System.out.println();
        }
        System.out.println();
    }
    
 
    public synchronized void makeChoice() {
        System.out.println("Choose in the following OptionSets:[Input option name]");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < opset.size(); i++) {
            System.out.println("OptionSet:" + opset.get(i).getOptionsetname());
            System.out.print("Available options are: ");
            for (int j = 0; j < opset.get(i).getOption().size(); j++) {
                System.out.print(opset.get(i).getOptionName(j));
                System.out.print(" , ");
            }
            System.out.println(" :now enter your choice..");
            String chose = scanner.nextLine();
            boolean match = false;
            for (int j = 0; j < opset.get(i).getOption().size(); j++) {
                if (opset.get(i).getOption().get(j).getName().equals(chose)) {
                    opset.get(i).setChoice(opset.get(i).findoption(chose));
                    opset.get(i).getChoice().setName(chose);
                    opset.get(i).getChoice().setPrice(opset.get(i).getOption().get(j).getPrice());
                    match = true;
                }
            }
            if (!match) {
                System.err.println(" no match found ");
               }
        }
    }

    public synchronized void printChoice() {
        System.out.println("Choices are:");
        float sum=baseprice;
        for (int i = 0; i < opset.size(); i++) {
            OptionSet os = opset.get(i);
            Option choice = os.getChoice();
            sum+=choice.getPrice();
            System.out.println(choice.getName() + " : " + choice.getPrice());
        }
        System.out.println("The total price is: " + sum);

    }
    
    public synchronized ArrayList<String> getOptionSetsAsString() {
		ArrayList<String> opSetStrings = new ArrayList<String>();
		for(OptionSet opSet : opset)
			opSetStrings.add(opSet.getOptionsetname());
		return opSetStrings;
	}
    public synchronized ArrayList<String> getOptionsAsString(String optionSetName) {
		OptionSet selectedOptionSet = getOptionSet(optionSetName);
		ArrayList<String> options = new ArrayList<String>();
		for(Option option : selectedOptionSet.getOption())
			options.add(option.getName());
		return options;
		}
    
	
	public float getOptionPrice(String opsetname, String optionname){
		OptionSet opset = getOptionSet(opsetname);
		if (opset == null) {
			return -1;
		}
		return opset.findoption(optionname).getPrice();
	}

	
	public ArrayList<String> getAllOptionSetName(){
		ArrayList<String> s = new ArrayList<String>();
		for(OptionSet tmp: opset){
			s.add(tmp.getOptionsetname());
		}
		return s;
	}

    
  
    
}

