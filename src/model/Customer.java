package model;

//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Customer {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ceb_power_usage", "root", "#ramya2317");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String customer_id, String customerName, String address, int telephoneNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error nothing in the database.";
			}
			// create a prepared statement
			String query = " insert into customer(`customerid`,`customer_id`,`customer_name`,`address`,`telephoneNo`)"
					+ " values (?,?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customer_id);
			preparedStmt.setString(3, customerName);
			preparedStmt.setString(4, address);
			preparedStmt.setInt(5, telephoneNo);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Customer Id</th><th>Customer Name</th><th>Address</th><th>Telepohne No</th></tr>";
			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String customerid = Integer.toString(rs.getInt("customerid"));
				String customer_id = rs.getString("customer_id");
				String customer_name = rs.getString("customer_name");
				String address = rs.getString("address");
				int telephoneNo = rs.getInt("telephoneNo");

				// Add into the html table
				output += "<tr><td>" + customer_id + "</td>";
				output += "<td>" + customer_name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + telephoneNo + "</td>";
				
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-success' data-itemid='" + customer_id + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + customer_id + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String customerid,String customer_id, String customer_name, String address, int telephoneNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE customer SET customer_id=?, customer_name=?, address=?, telephone_no=? WHERE customerid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customer_id);
			preparedStmt.setString(2, customer_name);
			preparedStmt.setString(3, address);
			preparedStmt.setInt(4, telephoneNo);
			preparedStmt.setString(5, customerid);
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating a customer details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCustomer(String customerid) {
		String output = " ";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customer where customerid=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, customerid);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the appointment detail.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String getCustomerPowerUsageByEmployee(int employeeId)
	{
		String output = "";
		 JSONArray jsonArray = new JSONArray();
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			String query = "select e.employee_id,e.employee_name,p.units,p.amount,p.customer_id from power_usage p,employee e where e.employee_id = p.employee_id AND e.employee_id = " + employeeId;
			PreparedStatement preparedStmt = con.prepareStatement(query);
//			preparedStmt.setInt(1, customerId);
			// binding values
			ResultSet rs = preparedStmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				int columns = rs.getMetaData().getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 0; i < columns; i++)
		            obj.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getObject(i + 1));
		 
		        jsonArray.put(obj);
			}
			con.close();
			// Complete the html table
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return jsonArray.toString();
	}
}
