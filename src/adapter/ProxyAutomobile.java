package adapter;

/** this class is to create and build automobile objects either using text file as input or using Properties profile from client
 * Created by santa on 6/11/15.
 */
import model.Automobile;
import util.FileIO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.io.ObjectOutputStream;
import exception.AutoException;
import model.Fleet;


public abstract class ProxyAutomobile implements Serializable {


    protected static Fleet f1 = new Fleet();
    protected static Automobile a1 = new Automobile();
    protected ArrayList<String> modelList;
    protected static LinkedHashMap<String, Automobile> autocarHash = new LinkedHashMap<String, Automobile>();


	public static LinkedHashMap<String, Automobile> getautocarHash() {
		return autocarHash;
	}

    /*option name update using multithreading if it has to come via interface */
    public void changeOptionName(String model, int index, int index2, String optionname) {
        f1.getAutomobile(model).setOptionname(index, index2, optionname);
    }


    /*option  update using multithreading if it has to come via interface*/
    public void changeOptionPrice(String modelname, String optionname, String option, float newprice){
        f1.getAutomobile(modelname).updateOptPrice(optionname, option, newprice);
    }

    /*with interface update option set name*/
    public void updateOptionSetName(String model, String optionsetname, String newname){
        for(int i=0;i<f1.getAutomobile(model).opset.size();i++)
        {
            if(f1.getAutomobile(model).getOptionSetName(i).equals(optionsetname))
                f1.getAutomobile(model).setOpsetname(i, newname);
        }
    }
        /*with interface update option price*/
    public void updateOptionPrice(String modelname, String optionname, String option, float newprice){
        f1.getAutomobile(modelname).updateOptPrice(optionname, option, newprice);
    }


    public void printAuto(String model){
       //a1=mobileHash.get(model);
        f1.getAutomobile(model);
        f1.printAutomobile(model);
       // a1.print();
    }
    //testFileName Method will try to replicate the error in try block, will pass the errornumber and errorcode to the AutoException class
    public void testFileName(String filename) throws AutoException{
        if(filename.equals("inputf.txt")==false)
            throw new AutoException(101, (" filemame " +filename+ " is not correct "));
    }

    public void buildAuto(String filename){
        try {

            if(filename.endsWith(".txt")) {
                FileIO f2 = new FileIO();
                testFileName(filename); // Usage via FixAuto Interface
                f2.buildAutoObject(filename, a1); //to build the automobile object after reading file
                autocarHash.put(a1.getModel(), a1);
                f1.setAutomobile(a1.getModel(), a1); //to build the fleet object from automobile object
            }

            if(filename.endsWith(".Properties")){ /*in case file has to come from Properties file*/
                try {

                    a1 = FileIO.usingProperites(filename);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }

                autocarHash.put(a1.getModel(), a1);

                System.out.println(" now writing a2.Properties on server...");

                a1.printauto();
                
                //f1.setAutomobile(a1.getModel(),a1); //using fleet object populating automobile which has autoHash of Automobiles
                //f1.getAutomobile(a1.getModel()).printauto(); // printing the values received from client from properties file
            }

        } catch (AutoException e1) {
            System.out.println();
            System.out.println(" error number " + e1.getErrorno() + " message " + e1.getErrormessage());
            e1.fixError(); //this will call method of Autoexception to build the file again.
            System.out.println();

        }

    }
    /* to get the models of the car*/
    public ArrayList<String> getMlist(){

        modelList = new ArrayList<String>();
        Iterator<Entry<String,Automobile>> iterator = autocarHash.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Automobile> entry = iterator.next();
            modelList.add(entry.getKey());
        }
        return modelList;
    }
    /*trying to send the list to client*/
    
    public void sendList(ObjectOutputStream s, ArrayList<String> list){
    	ArrayList<String> List = list;
    			
    			try{
    				s.writeObject(List);
    				s.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    }
    
    public void sendSelectedAutoList(ObjectOutputStream s, LinkedHashMap<String, Automobile> autoList2){
    	LinkedHashMap<String, Automobile> autoList = autoList2;
    			
    			try{
    				s.writeObject(autoList);
    				s.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    }
    

        /* sending the selected automobile to the client*/
    public void sendSelectedAuto(ObjectOutputStream s, String modelname) {
        Automobile Auto = autocarHash.get(modelname);
        if(Auto == null) {
            System.err.println("No  models available in server");
        }
        try {
            s.writeObject(Auto);
            s.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public float getprice(String mod) {
		Automobile a= f1.getAutomobile(mod);
		return(a.getBaseprice());
	}
	
    public float getOptionsprice(String model, String optionsetname, String optionname){
    	Automobile a1=f1.getAutomobile(model);
    	return a1.getOptionPrice(optionsetname,optionname);
    }
	
	public ArrayList<String> getalloptionsets(String mod){
		System.out.println("value of model.."+mod);
		//Automobile a1
		 System.out.println(autocarHash.size());
		//System.out.println("value of model......."+ a1.toString());
		return(a1.getOptionSetsAsString());
	}
	
	public ArrayList<String> getAllOptionSetName(String modelname){
		autocarHash.get(modelname).printauto();
		Automobile al = autocarHash.get(modelname);
		return al.getAllOptionSetName();
	}

	
	

}
