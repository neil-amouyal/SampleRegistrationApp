package sampleregistry;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Register
 * 
 * This servlet will be the entry point for new registrants during the registration process.
 * A form found on index.html will pass parameters to the servlet and the servlet will insert the new records
 * if the data meets the criteria defined in the FieldValidator class.
 * 
 * @author Neil Amouyal
 */
@WebServlet(asyncSupported = true, description = "Registeration entry point", urlPatterns = { "/Register" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet() 
     */
    public Register() {
        super();
        
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 	
	 * Post method to receive the form parameters, validate, and then try to insert into the database.
	 * Connection to the database is not established until all the fields have been validated through the FieldValidator class.
	 * @author Neil Amouyal
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("firstname");
		String lname = request.getParameter("lastname");
		String address1 = request.getParameter("adrs1");
		String address2 = request.getParameter("adrs2");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zipcode");
		String country = request.getParameter("country");
		PrintWriter out = response.getWriter();
		
		if(!FieldValidator.validateNameFields(fname) && !FieldValidator.validateNameFields(lname))
		{
			out.write("Error in name fields. First: "+fname + "\tLast: " + lname);
			return;
		}
		if(!FieldValidator.validateCountryField(country))
		{
			out.write("Error in country code. Country: " + country);
			return;
		}
		if(!FieldValidator.validateZipField(zip))
		{
			out.write("Error in zip field. Zip: " + zip);
			return;
		}
		if(!FieldValidator.validateState(state))
		{
			out.write("Error in state field. State: " + state);
			return;
		}
		
		//After validation we should be clear for storage
		DatabaseAP db = new DatabaseAP();
		try {

	        if (db.insertRecord(fname, lname, address1, address2, city, state, zip, country))
	        {
	        	out.write("Thank you for Entering! You are now enrolled in the system.");
	        }
	        else
	        {
	        	out.write("Error detected trying to enter information into the database. Ask admin to check server logs.");
	        }
		}
		catch (Exception e)
		{
			out.write("Error detected trying to enter information into the database. Ask admin to check server logs.");
			System.out.println("Exception thrown at insertRecord");
			e.printStackTrace();
		}
	}
}
