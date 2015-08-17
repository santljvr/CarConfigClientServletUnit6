
package servlet;
//main servlet where we are running the Tomcat Server. This file is "/getmodel" on the webpage and will link to further pages
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import client.DefaultSocketClient;
import model.Automobile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/model1")
public class servletClient extends HttpServlet {
	//private static DefaultSocketClient sc1;
	private static final long serialVersionID = 1L;
	private static Automobile auto;
	 private ArrayList<String> scmodels = new ArrayList<String>();
	static DefaultSocketClient ds = null;	
	
	@Override
	public void init(ServletConfig config)
	{
	 ds = new DefaultSocketClient("127.0.0.1",4444);
	 ds.start();
	System.out.println("Client is up...");
	}
	
	private ArrayList<String> models= new ArrayList<String>();
	//private static Socket socket;
	
	public ArrayList<String> getScmodels(){
		return scmodels;
	}	
	
	public void getAvailableModels(ArrayList<String> models) {
		
		//@SuppressWarnings("unchecked")
		scmodels = models;
	    System.out.println("model list inside servlet as received by client: "+ scmodels.toString());
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws 
	ServletException, IOException{
		HttpSession session = req.getSession(true);
	
		PrintWriter out = resp.getWriter();
		 String docType =
			      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
			      "Transitional//EN\">\n";
			    out.println(docType +
			                "<HTML>\n" +
			                "<HEAD><title>Car models</title></HEAD>\n" +
			                "<BODY>" +
			                "<H1>Models</H1>\n");
			    out.println("your turn to choose");
			    out.println("<form method=\"get\" action=\"./getoption\">");
			    for(String str : ds.getMl()){
			    	StringBuffer strBuff = new StringBuffer();
			    	strBuff.append("<input type=\"radio\" name=\"model\" value=");
			    	strBuff.append(str);
			    	strBuff.append(">");
			    	out.println(strBuff.toString());
			    	out.println(str);
			    }
			    out.println("<input type=\"submit\" value=\"Submit\"></form>");
			    out.println("</BODY></HTML>");

	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws 
	ServletException, IOException{
	}

}
