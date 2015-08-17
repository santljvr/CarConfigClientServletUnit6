package model;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by santa on 6/5/15.
 */
public class OptionSet implements Serializable {

    private static final long serialVersionUID = 42L;


    public class Option implements Serializable {

        private static final long serialVersionUID = 42L;


        private String name;
        private float price;



        Option(){
            super();
        }

        protected String getName() {
            return name;
        }
        protected void setName(String name) {
            this.name = name;
        }
        protected float getPrice() {
            return price;
        }
        protected void setPrice(float price) {
            this.price = price;
        }

        private void print() {
            StringBuffer sb = new StringBuffer();
            sb.append(name);
            sb.append(price);
            System.out.println();
            System.out.format("%d%d%d%d", sb);
        }

    }
    
    

        private ArrayList<Option> option = new ArrayList<Option>();

        private int number; // will be used for lenght calculation

        protected int getNumber() {
        return this.option.size();
    }

        protected void setNumber(int number) {
        this.number = number;
    }

        protected Option choice = new Option();

    private String optionsetname;
    
    
	public ArrayList<String> getAlloptions(){
		ArrayList<String> s = new ArrayList<String>();
		for(Option tmp: option){
			s.add(tmp.getName());
		}
		return s;
	}

    

    protected void setOptionsetname(String optionsetname) {
        this.optionsetname = optionsetname;
    }

    protected String getOptionsetname() {

        return optionsetname;
    }



    protected OptionSet(){
        super();
    }

    protected void setChoice(Option choice){
        this.choice = choice;
    }

    public Option getChoice() {
        return choice;
    }

    protected ArrayList<Option> getOption() {
        return option;
    }

    protected void setOption(ArrayList<Option> option) {
        this.option = option;
    }

    protected void setOption2 (int optionsize) {

        for (int jk=0; jk<optionsize; jk++){
            Option option12 = new Option();
            option.add(option12);

        }

    }


    protected String getOptionName(int optionindex){
    return option.get(optionindex).getName();
    }

    protected float getOptionPrice(int optionindex2){
        return option.get(optionindex2).getPrice();
    }


    protected void print() {
        StringBuffer sb = new StringBuffer();
        sb.append(" optionset ");
        sb.append(optionsetname);
        System.out.print("  " + sb);
        System.out.println();


        for (int i = 0; i < option.size(); i++) {
            System.out.print( " " + option.get(i).getName()+ " " + option.get(i).getPrice());
        }
    }


    protected void setOptionName(int index2, String oname1) {
        if(index2<option.size())
            option.get(index2).setName(oname1);
    }

    protected int getOptionIndex(String name1){
        for(int j=0; j<option.size(); j++) {
            if (option.get(j).name == name1)
                return j;
        }
        return -1;
    }

    protected void setOptionPrice(String name , Float opprice1){
        option.get(getOptionIndex(name)).setPrice(opprice1);

    }



    //to find option by name

    protected Option  findoption(String optionname){
        for(int i=0; i<option.size(); i++){
            if(option.get(i).name.equals(optionname)){
                return option.get(i);
            }
        }
        return null;
    }

  /*  protected  void setoptionprice(String string1, Float price){
        int index = getOptionIndex(string1);
        option.get(index).setPrice(price);
    }*/

    protected void updateOptPrice(String optName1,float price1)
    {
        findoption(optName1).setPrice(price1);
    }
    //to delete an option
    protected void deleteoption(int index){

        option.get(index).setName("null");
    }

    //update options with new set of options for any given option set
    protected ArrayList<Option> updateoption(int index, String name, float price){
        for(int i=0; i<option.size(); i++) {
            System.out.println(" at index equals to " + i);
            System.out.println(" current option name at index  = "+ option.get(i).getName());
            if(i==index) {
                System.out.println(" changing the name of the option at this index ");
                option.get(i).setName(name);
                System.out.println( " New values are " + option.get(i).getName());
            }
        }
        return option;
    }

}
