package client;

/**
 * Created by  on 6/23/15.
 * client class to read the properties file and configure it later based upon user inputs
 */

//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import model.Automobile;

import java.io.*;
import java.net.*;


 public class CarModelOptionsIO {
	 
	 Automobile auto = new Automobile();
	 
		public Automobile getAutoObject(Socket socket){
			auto = null;
			try {
				if (socket.isClosed()) return null;
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				auto = (Automobile) in.readObject();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Automobile class not found");
				e.printStackTrace();
			}
			return auto;
		}		

     /*method to read the property values from the file*/
     protected void getPropertyValues(Socket clientSocket, String propertyfilename) throws IOException {

         String propFileName = propertyfilename;
         String fpath = ("/Users/santa/IdeaProjects/unit5/"+propFileName);
         File transferFile = new File(fpath);
         byte[] bytearray= new byte[(int)transferFile.length()];
         FileInputStream fin = new FileInputStream(transferFile);
         BufferedInputStream bin = new BufferedInputStream(fin);
         bin.read(bytearray,0,bytearray.length);
         OutputStream os =  clientSocket.getOutputStream();
         System.out.println("sending file to server"); //this will send file back to SocketClinet and then to ServerSocket.
         os.write(bytearray, 0, bytearray.length);
         os.flush();
         clientSocket.close();
         System.out.println();

     }
     
     /* method for configuring the automobile selected by user*/
     protected   void configureAuto(Automobile automobile) throws Exception {
         automobile.printauto();
         System.out.println("...please configure your model...");
         automobile.makeChoice();
         automobile.printChoice();

     }

 }
