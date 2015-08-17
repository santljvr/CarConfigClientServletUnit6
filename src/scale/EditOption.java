package scale;

/**
 * Created by santa on 6/17/15.
 */

import model.Automobile;

public class EditOption extends Thread implements Scalable{

private Automobile automobile;

        int action;

    /*constructor to inform if we want to edit options or not */

    public EditOption (int action, Automobile automobile){
        this.action=action;
        this.automobile=automobile;
    }


    /*option price  update */
    public synchronized void changeOptionPrice(int index, int index2, float price) {

        automobile.setOptionprice(index, index2, price);
    }

    /*option name update*/
    public  synchronized void changeOptionName(int index, int index2, String optionname) {
        automobile.setOptionname(index, index2, optionname);
    }



    public synchronized String getOptionname(int index, int index2){
        return automobile.getOptionName(index, index2);

    }

    public synchronized float getOptionPrice(int index, int index2){
            return automobile.getOptionPrice(index,index2);
    }

     /* Randomly wait*/

    void randomWait(){
        try{
            Thread.currentThread().sleep(300);
        } catch (InterruptedException e) {
            System.out.println(" error in thread - interruption ");
        }
    }

    public void run() {
        synchronized (System.out) {
            if(action==1) {
                thread1();
            }
            else if(action==2) {
                thread2();
            }else {
                thread3();
            }
        }

    }

    private void thread1(){
        System.out.println(" Thread" + " update colors is running .. ");
        randomWait();
        changeOptionName(0, 0, "COLORSSSS");
        System.out.println(" thread 1 is finished updating option name: colors to COLORSSS");
    }

    public void thread2(){
        System.out.println(" thread" +  "  update price " + " is running ..");
        randomWait();
        changeOptionPrice(0, 0, 20000);
        System.out.println(" thread 2 is finished updating option price");
    }

    private void thread3(){
        System.out.println();
        System.out.println("Thread is running to get the data of updated name and price...");
        randomWait();
        System.out.println(" updated name using synchronization " + getOptionname(0, 0));
        System.out.println(" updated pirce using synchronization " + getOptionPrice(0, 0));

    }

}
