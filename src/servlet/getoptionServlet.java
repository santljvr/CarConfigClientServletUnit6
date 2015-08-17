package servlet;
//this class is to build the pages for car configuration and further path to see the configured car and prices/ (summary.jsp)
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
//import java.net.Socket;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.Iterator;

import adapter.BuildAuto;
import adapter.ProxyAutomobile;
import model.Automobile;

import model.OptionSet;
//import server.BuildCarModelOptions;

import java.util.Map.Entry;
import java.util.Set.*;

@WebServlet("/getoption")
public class getoptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	servletClient servletclient;	
	private HttpSession session;
	BuildAuto buildAuto = new BuildAuto();
	//private boolean timer = true;
	

	public getoptionServlet() {
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
	
		HttpSession session = req.getSession(true);
		Automobile a1 = new Automobile();
		
		String docType="<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
						"Transitional//EN\">\n";

		String model = req.getParameter("model");
		
		System.out.println("..mode inside options is : "+ model);
		
		if(model !=null){
			System.out.println("selected model name is : " + model);
			out.println(model);
		
			System.out.println(servletclient.ds.autoList.size());
					
			java.util.Iterator<Entry<String, Automobile>> iterator =   servletclient.ds.autoList.entrySet().iterator();
            
            while (iterator.hasNext()) {
                Entry<String, Automobile> entry = iterator.next();
                if( servletclient.ds.autoList.get(entry.getKey()).getModel().equals(model))
                	a1=  servletclient.ds.autoList.get(entry.getKey());
            }    
            System.out.println("base price"+a1.getBaseprice());
            
		
		}	

	
		out.println(docType + "<HTML>\n"
				+ "<HEAD><TITLE>CONFIGURE YOUR CAR</TITLE></HEAD>\n"
				+ "<BODY>\n"
				+ "<H1>CONFIGURE YOUR CAR </H1>\n "
				+ "<TABLE BORDER=2 ALIGN=\"CENTER\">\n"
				+ "<TR BGCOLOR=\"#FFWE02\">\n" + "  <TH>Feature<TH>Value\n"
				+ "<TR>\n" + "  <TD>Choose:\n" + "<TD>\n" );

		out.println(req.getParameter("model"));
		out.println("<form method=\"post\" action=\"./summary.jsp\">");
		out.println("<option>" + model + "\n");
		out.println("</select>\n" );
		
	ArrayList<String> opsetName = new ArrayList <String>();
    int [] count = new int[10];
    int l = 0;
		 for(int j=0; j<a1.getAllOptionSetName().size(); j++){
	       	 count[l]= a1.getNumOption(j);
				 out.println(" <TR>\n" + "   <TD>" + a1.getOptionSetName(j) + ":\n" + "    <TD>"
							+ "<select name =" +  "\"" + a1.getOptionSetName(j) + "\"" +">\n");
             for(int k=0; k<count[l]; k++){
			 out.println(" <option>" + a1.getOptionName(j,k)+"\n");
			 session.setAttribute("price" +a1.getOptionSetName(j),a1.getOptionPrice(j,k));	
             }
             l=l+1;
		 }
				
		session.setAttribute("auto", a1);
		session.setAttribute("model", model);
		session.setAttribute("opsetList", a1.getAllOptionSetName().toString());  
		session.setAttribute("BasePrice", a1.getBaseprice());
		session.setAttribute("make", a1.getMake());

		out.println("<TR>\n");
		out.println("<input type=\"submit\" value=\"Submit\"></form>");
		out.println("</TABLE>");
		out.println("</BODY></HTML>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}



}


