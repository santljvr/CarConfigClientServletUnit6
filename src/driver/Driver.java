package driver;

import adapter.BuildAuto;
import model.Automobile;
import scale.EditOption;
import util.FileIO;
import util.Serialize;

import java.util.Scanner;

/**
 * Created by santa on 6/5/15.
 */
public class Driver{


    public static void main(String[] args) {

        BuildAuto build1 = new BuildAuto();

        Automobile auto1 = new Automobile();



       FileIO f1 = new FileIO();





        auto1= f1.buildAutoObject("inputf.txt",auto1);
        {


            for(int ltr=0; ltr<auto1.opset.size(); ltr++){

                System.out.println("optionset " + auto1.getOptionSetName(ltr));
                System.out.print(" options ");

                for(int btr=0; btr<(auto1.getNumOption(ltr)); btr++) {

                    System.out.print(auto1.getOptionName(ltr, btr) + " ");

                   System.out.print(auto1.getOptionPrice(ltr, btr) + " ");
                }
                System.out.println(" ");
            }

            }
        System.out.println();
        System.out.println();



        //now below is the interface code
        System.out.println(" ...now below  is interface test and fleet and automobile objects are created after this...");

        build1.buildAuto("inpu.txt"); //this one is to read the file and build objects again
        //but this one has mitake in the file name so it will call the custom exception
        System.out.println();
        build1.printAuto("SX4");
        System.out.println();
        build1.updateOptionSetName("SX4", "wipers", "WIPERS"); //updating the wipers to "WIPERS" (optionset)

        build1.updateOptionPrice("SX4", "breaks", "manual", 150);//updating the price of manual breaks to 150

        System.out.println();
        System.out.println();


        System.out.println(".....after update process....");

        build1.printAuto("SX4");


        System.out.println("this was interface and update optionprice and updateoption name via interface test .price of 'manual' is changed from 90 to 150 and name of 'wipers' is changed to 'WIPERS'..");


                /* code for interface ends...*/
        System.out.println();
        System.out.println();

        System.out.println(" .....below is the multithreading test..... ");

                        EditOption t1 = new EditOption(1,auto1);
                        EditOption t2 = new EditOption(2,auto1);
                        EditOption t3 = new EditOption(1,auto1);
                        EditOption t4 = new EditOption(2,auto1);
                        EditOption t5 = new EditOption(3,auto1);
                        EditOption t6 = new EditOption(3,auto1);


                System.out.println("...... running t1 and t4 threads... to update value  ..");

                        t1.start();
                        t2.start();
                        t3.start();
                        t4.start();

                System.out.println(" ......running t5 to t6 threads ...to read values....");

                        t5.start();
                        t6.start();

        System.out.println(" ..java.scheduler is sometimes displaying print instructions which are outside of these synchronized methods in different ...order however all the seralized methods of Edit Options are running in sequesnce");



        /*code for serialization*/


            Serialize ser = new Serialize();

            ser.SerializeAuto(auto1); // serialization of auto


            Automobile autotest = ser.DeserializeAuto("auto.ser.txt"); //deserialization of auto


        System.out.println();
        System.out.println(" serialization & deserialization ...test of assignement 2  ");
        System.out.println("to test the deserilazed auto : Price of the car "+ autotest.getAutoprice() + " "+ autotest.getAutoname() + " with option set " + autotest.getOptionSetName(1) + "  and option " + autotest.getOptionName(1,1) + " at price of  " + autotest.getOptionPrice(1,1));
        System.out.println();
        System.out.println();
        System.out.println();



            /* code for update option choice*/



        System.out.println(" .......this is option choice test...of assignement 3.....");
        build1.buildAuto("inputf.txt"); // this one isto build objects for choice test

        Scanner in = new Scanner(System.in);
        System.out.println(" enter your choice of  color");
        String optionchoicecolor = in.nextLine();
        auto1.setOptionchoice("colors", optionchoicecolor);
        System.out.println(" enter your choice of breaks ");
        String optionchoicebreaks = in.nextLine();
        auto1.setOptionchoice("breaks", optionchoicebreaks);
        System.out.println(" enter your choice of roofs ");
        String optionchoiceroofs = in.nextLine();
        auto1.setOptionchoice("roofs", optionchoiceroofs);
        System.out.println(" enter your choice of wipers ");
        String optionchoicewipers = in.nextLine();
        auto1.setOptionchoice("wipers", optionchoicewipers);

        System.out.println();
        System.out.println("  the total price including these options would be " + auto1.getTotalprice());
        System.out.println();


        System.out.println("..part of assignment 1 below is the code to CRUD operations part of assignment 1.. it's been tested.");


        System.out.println(" now testing with find, update and delete methods ");


        //For testing of update, delete and find functions


       // Scanner in = new Scanner(System.in);



        System.out.println(" enter the Option you want to 1.find ,2.update,3.delete. ");
        String keyword = in.nextLine();

        if (Integer.parseInt(keyword) == 1) {
            System.out.println("Enter Optionset name ");
            String keyword2= in.nextLine();
            int op = auto1.findoptionset(keyword2);
            if(op == -1)
                System.out.println("Optionset not found ");
            else
                System.out.println("Optionset found at " + op);


        }
        // to update the optionset with a keyword

        //System.out.println("now enter the optionset name to update at this index ");

        //String keyword2= in.nextLine();
        if (Integer.parseInt(keyword) == 2) {
            System.out.println("Enter Index of Optionset to be updated ");
            String keyword3= in.nextLine();
            System.out.println("Enter new name ");
            String keyword4= in.nextLine();
            auto1.updateoption( Integer.parseInt(keyword3),keyword4);

            for(int ltr=0; ltr<auto1.opset.size(); ltr++){

                System.out.println("optionset " + auto1.getOptionSetName(ltr));
                System.out.print(" options ");

                for(int btr=0; btr<(auto1.getNumOption(ltr)); btr++) {

                    System.out.print(auto1.getOptionName(ltr, btr) + " ");

                    System.out.print(auto1.getOptionPrice(ltr, btr) + " ");
                }
                System.out.println(" ");
            }
        }
        //System.out.println(" now enter the optionset index to update ");
        //String keyword3 = in.nextLine();


        if (Integer.parseInt(keyword) == 3) {
            System.out.println("Enter Optionset index ");
            String keyword4= in.nextLine();
            int j= Integer.parseInt(keyword4);
            auto1.deleteoptionset(j);

            for(int ltr=0; ltr<auto1.opset.size(); ltr++){

                System.out.println("optionset " + auto1.getOptionSetName(ltr));
                System.out.print(" options ");

                for(int btr=0; btr<(auto1.getNumOption(ltr)); btr++) {

                    System.out.print(auto1.getOptionName(ltr, btr) + " ");

                    System.out.print(auto1.getOptionPrice(ltr, btr) + " ");
                }
                System.out.println(" ");
            }
        }




        //to run the code for Update Options using multithreading

        // now testing synchronization as given in assignment 4;

       /* System.out.println(" .... trying to test synchronization ....");

        EditOption ed1 = new EditOption(0,auto1);
        EditOption ed2 = new EditOption(1,auto1);

        ed1.start();

        System.out.println("..... testing synchronization  .......");

        ed2.start();

        */









        // System.out.println(" now deleting the values ");



/*
        System.out.print(" please enter the option set we are working to change -  ");
        String keyword4 = in.nextLine();

        System.out.println(string);

        String string2 = new String();
        string2=auto1.getOptionName(1,1);
        System.out.print(" the option name we are working to chage  - ");
        System.out.println(string2);

*/


    }


}
