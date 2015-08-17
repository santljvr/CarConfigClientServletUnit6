package util;
//import com.sun.deploy.util.SyncFileAccess;
import model.Automobile;
import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;


/**
 * Created by santosh on 6/4/15.
 *
 * this will generate an instance of fileIo take filename and
 * initialise auot1 with default values for make and model
 *
 * it has also geting and puting the properties file and building automobile objects
 *
 */



public class FileIO {

        static public Automobile usingProperites(String filename) throws IOException {
            Automobile automobile = new Automobile();
            automobile.setOptionSet(4);

             Properties props = new Properties();
            FileInputStream in = new FileInputStream(filename);
            props.load(in);

             String carmake = props.getProperty("CarMake"); //make

            if (!carmake.equals(null)) {

                String carmodel = props.getProperty("CarModel");  //model
                String carBasePrice = props.getProperty("CarBasePrice"); //baseprice
                String optionsetname1 = props.getProperty("Option1"); //optionset1 colors
                String optionname1a = props.getProperty("Optionname1a"); //red
                String optionprice1a = props.getProperty("Optionprice1a"); //price of red 10
                String optionname1b = props.getProperty("Optionname1b");  //blue
                String optionprice1b = props.getProperty("Optionprice1b"); //price of blue 20
                String optionsetname2 = props.getProperty("Option2");   //breaks
                String optionname2a = props.getProperty("Optionname2a");   //manual
                String optionprice2a = props.getProperty("Optionprice2a");
                String optionname2b = props.getProperty("Optionname2b");    //abs
                String optionprice2b = props.getProperty("Optionprice2b");
                String optionsetname3 = props.getProperty("Option3");      //
                String optionname3a = props.getProperty("Optionname3a");
                String optionprice3a = props.getProperty("Optionprice3a");
                String optionname3b = props.getProperty("Optionname3b");
                String optionprice3b = props.getProperty("Optionprice3b");
                String optionsetname4 = props.getProperty("Option4");
                String optionname4a = props.getProperty("Optionname4a");
                String optionprice4a = props.getProperty("Optionprice4a");
                String optionname4b = props.getProperty("Optionname4b");
                String optionprice4b = props.getProperty("Optionprice4b");


                automobile.setAutoname(carmake); //seting up Make of the automobile
                automobile.setModel(carmodel);   //seting up Model of the automobile
                automobile.setAutoprice(Float.parseFloat(carBasePrice)); //setting up the base price of the car

                automobile.setOpsetname(0, optionsetname1); //setting up optionsetnames like colors, breaks, roofs, wipers
                automobile.setOpsetname(1, optionsetname2);
                automobile.setOpsetname(2, optionsetname3);
                automobile.setOpsetname(3, optionsetname4);

                automobile.setOption(0, 2); //setting up options
                automobile.setOption(1, 2);
                automobile.setOption(2, 2);
                automobile.setOption(3, 2);

                automobile.setOptionname(0, 0, optionname1a); //setting up option names
                automobile.setOptionname(0, 1, optionname1b);
                automobile.setOptionname(1, 0, optionname2a);
                automobile.setOptionname(1, 1, optionname2b);
                automobile.setOptionname(2, 0, optionname3a);
                automobile.setOptionname(2, 1, optionname3b);
                automobile.setOptionname(3, 0, optionname4a);
                automobile.setOptionname(3, 1, optionname4b);


                automobile.setOptionprice(0, 0, Float.parseFloat(optionprice1a)); //setting up option prices
                automobile.setOptionprice(0, 1, Float.parseFloat(optionprice1b));
                automobile.setOptionprice(1, 0, Float.parseFloat(optionprice2a));
                automobile.setOptionprice(1, 1, Float.parseFloat(optionprice2b));
                automobile.setOptionprice(2, 0, Float.parseFloat(optionprice3a));
                automobile.setOptionprice(2, 1, Float.parseFloat(optionprice3b));
                automobile.setOptionprice(3, 0, Float.parseFloat(optionprice4a));
                automobile.setOptionprice(3, 1, Float.parseFloat(optionprice4b));

            }
            return automobile;
        }


       public Automobile buildAutoObject(String filename, Automobile auto1) {

               String line1 = null;
               String line = null;

               int j = 0;
               int count = 0;


               try {

                   FileReader file1 = new FileReader(filename); //filereader and string tokenizer to read the file

                   BufferedReader buff = new BufferedReader(file1);

                   while ((line = buff.readLine()) != null) {
                       StringTokenizer temp = new StringTokenizer(line, " ");
                       j = j + 1; // j is number of optionsets
                   }
                   buff.close();
               } catch (FileNotFoundException f1) {

               } catch (IOException e2) {
                   System.out.println(" Error in the file '" + filename + "'");
               }

               System.out.println(" the value of number of lines in file i.e. number of option sets = " + j);


               int s = 0;


               String model2 = "SX4";  //defaulting model to SX4 and make to maruti

               auto1.Automobile(model2, j, "maruti", 1800); //j will set the number of lines in the file i.e. number of optionsetss


               int numopt[] = new int[j];

               try {

                   FileReader file = new FileReader(filename);

                   BufferedReader buff2 = new BufferedReader(file);

                   while ((line1 = buff2.readLine()) != null) {

                       //StringTokenizer auto = new StringTokenizer(line1," ");

                       //test
                       StringTokenizer st2 = new StringTokenizer(line1, " ");
                       String str = st2.nextToken();
                       while (st2.hasMoreElements()) {
                           count++;
                           str = st2.nextToken();

                       }
                       //
                       numopt[s] = count;


                       StringTokenizer st3 = new StringTokenizer(line1, " ");
                       auto1.setOpsetname(s, st3.nextToken());
                       auto1.setOption(s, count);

                       for (int c = 0; c < count; c++) {
                           StringTokenizer st4 = new StringTokenizer(st3.nextToken(), ":");
                           auto1.setOptionname(s, c, st4.nextToken());


                           auto1.setOptionprice(s, c, Float.parseFloat(st4.nextToken()));

                       }
                       //auto1.setNumberofoptions(s,count);
                       auto1.setNumOption(s, count);

                       s = s + 1;
                       count = 0;

                   }
                   buff2.close();
               } catch (FileNotFoundException ex) {
                   System.out.println("Error Unable to open file '" + filename + "'");
               } catch (IOException ex1) {
                   System.out.println(" Error in the file '" + filename + "'");
               }

           return auto1;

       }
}
