/**
 * 
 */
package sampleregistry;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Database Layer - this class will contain all the database functionality to expose to the rest of the program.
 * Two simple methods are written here to take advantage of the stored procedures in the database.
 * One insert method to add new content
 * and a reader method to collect all records entered into the database so far. 
 * Any new database functionality should go here.
 *
 */
public class DatabaseAP 
{
	private final String constring;
	private final String user;
	private final String pass;
	private final String driver;
	private final String insertProc;
	private final String readProc;

	public DatabaseAP()
	{
		constring = "jdbc:mysql://localhost:3306/sampleregistry";
		user = "reader";
		pass = "reader212";
		driver = "com.mysql.jdbc.Driver";
		insertProc = "{call insert_registrant(?,?,?,?,?,?,?,?)}";
		readProc = "{call get_registrants()}";
	}

	/**
	 * getRecords calls the database stored procedure 'get_registrants'
	 * which is a wrapper for a select statement that orders by date entered (descending)
	 * The data is passed back in an ArrayList of String arrays for easy appending and traversal.
	 * 
	 * This function should take page-size and start-index parameters for larger data sets.
	 * 
	 * @return ArrayList&gt;String[]&lt;
	 */
	public ArrayList<String[]> getRecords()
	{
		ArrayList<String[]> records = new ArrayList<String[]>();
		Connection con = null;
		CallableStatement callStmt = null;
		ResultSet rs = null;
		try {
			//load driver
			Class.forName(driver);
			//create connection
			con = DriverManager.getConnection(constring, user, pass);
			callStmt = con.prepareCall(readProc);
			rs = callStmt.executeQuery();
			while(rs.next())
			{
				String[] row = new String[9];
				row[0] = rs.getString("firstName");
				row[1] = rs.getString("lastName");
				row[2] = rs.getString("address1");
				row[3] = rs.getString("address2");
				row[4] = rs.getString("city");//city
				row[5] = rs.getString("state");//state
				row[6] = rs.getString("zip");//zip
				row[7] = rs.getString("country");//country
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				row[8] = df.format(rs.getTimestamp("dateEntered"));
				records.add(row);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("ClassPath Issues.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL Exception Thrown.");
			e.printStackTrace();
		}
		return records;
	}
	
	/**
	 * insertRecord is a wrapper for the 'insert_registrant' stored proc.
	 * Validation on parameters occurs in the servlet layer so as 
	 * not to open the database connection unless all parameters have been validated.
	 * 
	 * @param fname first name field
	 * @param lname last name field
	 * @param address1 address1 field
	 * @param address2 address2 field
	 * @param city city field
	 * @param state two-letter state code
	 * @param zip 5 or 9 digit zip code
	 * @param country two-letter country code
	 * @return returns true if the entry has been inserted, false if not
	 * @throws SQLException throws an sql exception upon errors
	 */
	public boolean insertRecord(String fname, String lname, String address1, String address2,
			String city, String state, String zip, String country) throws SQLException
	{
		Connection con = null;
		CallableStatement callStmt = null;
		boolean insertionStatus = false;
		//creating connection with the database 
		try {
			//load driver
			Class.forName(driver);
			//make connection
			con = DriverManager.getConnection(constring, user, pass);
			callStmt = con.prepareCall(insertProc);
			//push in the parameters
			callStmt.setString(1, fname);
			callStmt.setString(2, lname);
			callStmt.setString(3, address1);
			callStmt.setString(4, address2);
			callStmt.setString(5, city);
			callStmt.setString(6, state);
			callStmt.setString(7, zip);
			callStmt.setString(8, country);
			
			if(callStmt.executeUpdate() > 0)
				insertionStatus = true;
		} catch (SQLException e) {
			System.out.println("Sql Exception thrown.");
			e.printStackTrace();
			insertionStatus = false;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassPath issues.");
			e.printStackTrace();
			insertionStatus = false;
		}
		finally
		{
			if(con != null)
				con.close();
			if(callStmt != null)
				callStmt.close();
		}
		return insertionStatus;
	}
}
