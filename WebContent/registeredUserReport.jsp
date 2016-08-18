<%@ page import="java.util.ArrayList" %>
<%@ page import="sampleregistry.DatabaseAP" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin: Registered Applicants</title>
<link rel="stylesheet" type="text/css" href="styles/userReport.css">
</head>
<body>
<h3>Registered Applicants:</h3>
<hr/>
<table id='mainDashTable' class='mainTable' border='0'>

<%
//first print headers
String[] headers = new String[] 
{
		"First Name", "Last Name", "Address1", "Address2", "City",
		"State", "Zip", "Country", "Date Registered"
};
StringBuffer sb = new StringBuffer();
sb.append("<tr class=\"header\">");
for(String header : headers)
{
	sb.append("<th>" + header + "</th>");
}
sb.append("</tr>");
out.write(sb.toString());

//Since this is a sample webpage with a small data set, just dump the whole contents of the table. 
//In practice this .jsp should take two parameters, pageSize and startIndex, and the DatabaseAP.getRecords()
//	should be overloaded to take these parameters and return the requested page of data.
// 	Appropriate links should be rendered to access the different page sizes and start indices. 
//		i.e. 10, 20, 50, 100, next, previous, first, last, etc.
ArrayList<String[]> data = new DatabaseAP().getRecords();
//output to table
int i=0;
for(String[] row : data)
{
	sb = new StringBuffer();
	sb.append("<tr class=\"");
	sb.append((i++ % 2 == 0 ? "even_row" : "odd_row"));
	sb.append("\"><td>" + row[0] + "</td>");
	sb.append("<td>" + row[1] + "</td>");
	sb.append("<td>" + row[2] + "</td>");
	sb.append("<td>" + row[3] + "</td>");
	sb.append("<td>" + row[4] + "</td>");
	sb.append("<td>" + row[5] + "</td>");
	sb.append("<td>" + row[6] + "</td>");
	sb.append("<td>" + row[7] + "</td>");
	sb.append("<td>" + row[8] + "</td></tr>");
	out.println(sb.toString());
}
%>
</table>
</body>
</html>