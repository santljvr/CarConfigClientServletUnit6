package exception;
import java.util.*;
import util.FileIO;

/**
 * Created by santosh on 6/12/15.
 *
 * //this is an AutoException class defined in the excetpion package which is used via ProxyAutomobile
 * interface while trying to read the file. This has custom exception and has fixError method to decide what
 * action to take based upon error code. Here for milling file or file not found file error is 101
 */
public class AutoException extends Exception {

    private int errorno;
    private String errormessage;

    Resolve101 b = new Resolve101();


    public AutoException(int errorno, String errormessage) {
        super(errormessage);
        this.errorno=errorno;
        this.errormessage=errormessage;
    }

    public AutoException(Throwable cause)
    {
        super(cause);
    }



    public int getErrorno(){
        return this.errorno;
    }

    public void fixError() {
        if (this.errorno == 101) {
            b.resolve();
        }
    }

    public String getErrormessage() {
        return errormessage;
    }
}



