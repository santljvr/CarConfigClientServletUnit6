package client;

import exception.AutoException;
import model.Automobile;
import servlet.servletClient;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map.Entry;

/**
 * Created by santa on 6/23/15.
 * Default Socket client class as described in the document
 */
public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants {

    private BufferedReader reader;
    private BufferedWriter writer;

    private Socket socket;
    private String strHost;
    private int iPort;
    protected ArrayList<String> ml = new ArrayList<String>();
    public CarModelOptionsIO carconfiguration = new CarModelOptionsIO();
    public  LinkedHashMap<String, Automobile> autoList = new LinkedHashMap<String, Automobile>();
    private Boolean clientPointer;

    protected void setClientPointer(Boolean clientPointer) {
        this.clientPointer = clientPointer;
    }

    ObjectOutputStream ps = null;
    BufferedOutputStream output = null;
    FileInputStream in= null;

    protected void setiPort(int iPort) {
        this.iPort = iPort;
    }
    
    public LinkedHashMap<String, Automobile> getAuto(){
    	return autoList;
    }

    protected void setStrHost(String strHost) {
        this.strHost = strHost;
    }
    
   
    public DefaultSocketClient(String strHost, int iPort) {
        setiPort(iPort);
        setStrHost(strHost);
    }//constructor using both ip address and port from constants


    public void setSocket(Socket socket){
    	this.socket=socket;
    }
    
    public Socket getSocket(){
    	return socket;
    }

    public boolean openConnection() {

        try{
            socket = new Socket(strHost,iPort);

            ps = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException socketError) {
            if(debug) System.err.println("unable to connect to server " + strHost);
            return false;
        }


        return true;
    }
    //@SupressWarnings'unchecked'
    public void handleSession() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("  Hello this is car configuration tool  using client server and Java socket");
            System.out.println(" enter: 'upload' or 'configure' ");
            String userOption = scanner.nextLine();
            //ps.flush();
            if (userOption.equals("upload")) {
                ps.writeObject("upload");
                //ps.flush();;
                System.out.println("Input filename from path '/project1_unit4_Santosh/(yourfilene)'... do not consider a2.Properties");
                String propertyFilename = scanner.nextLine();
                carconfiguration.getPropertyValues(socket, propertyFilename);
                
                
            } else if(userOption.equals("configure")) {
            
            	ps.writeObject("configure");                					
            	ps.flush();
            	
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                autoList =(LinkedHashMap<String, Automobile>) ois.readObject();

                //@SupressedWarnings unchecked
                 
                Iterator<Entry<String,Automobile>> iterator = autoList.entrySet().iterator();
                 
                while (iterator.hasNext()) {
                     Entry<String, Automobile> entry = iterator.next();
                     ml.add(autoList.get(entry.getKey()).getModel() );
                }
                         		
        		 //sc.getAvailableModels(ml);
               
                System.out.println("..model list coming out from client"+ ml.toString()); 
                // Automobile auto = (Automobile) ois.readObject();
                //carconfiguration.configureAuto(auto);
                
                setClientPointer(false);
            }
            ps.flush();
            System.out.println("client listened on  on host :"+ strHost + ": port" + iPort);
        } catch(Exception e){
            System.err.println(" their is an error  could not find the file on path /unit5");
        }
    }
    
    public ArrayList<String> getMl(){
    	return ml;
    }
    
   
    
    public void closeSession(){
        try {
            writer = null;
            reader = null;

            socket.close();

        }
        catch (IOException e){
            if (debug) System.err.println
                    ("Error closing socket to " + strHost);
        }
    }


    public void run(){

        setClientPointer(true);

        while(clientPointer) {
            if(openConnection()){
                handleSession();
               closeSession();
            }
        }
    } //run


    public static void main(String[] args) {
        /*debug main, daytime on local host*/

        String strLocalHost = "";
        try {
            strLocalHost = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException e){
            System.err.println("unable to find local host ");
        }
        DefaultSocketClient d = new DefaultSocketClient(strLocalHost, iDAYTIME_PORT);
        d.run();
        
    }

}
