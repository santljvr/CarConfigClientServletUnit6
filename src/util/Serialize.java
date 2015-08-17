package util;

import model.Automobile;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.*;

/**
 * Created by santosh on 6/6/15.
 */

//this will seralize and deseralize the code

public class Serialize {

     public void SerializeAuto(Automobile auto) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("auto.ser.txt"));
            out.writeObject(auto);
            out.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }
    }

     public Automobile DeserializeAuto(String filename) {
        try {
            Automobile auto;
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            auto = (Automobile) in.readObject();
            in.close();
            return auto;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
