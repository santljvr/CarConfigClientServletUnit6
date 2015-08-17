package exception;
import adapter.BuildAuto;

/**
 * Created by santosh on 6/12/15.
 */
//This will be called inside AutoException class for correcting file not found (which is customized exception)
//As per the 101 code for file not found exception error this will run and  build using the correct file name

public class Resolve101 {

    BuildAuto b1 = new BuildAuto(); //object of build auto

    public void resolve(){

        String filename1 = "inputf.txt"; //correct file name
        System.out.println();
        System.out.println(" after input the correct file name ");
        System.out.println();

        b1.buildAuto(filename1); // running build auto with correct file name and location
    }
}
