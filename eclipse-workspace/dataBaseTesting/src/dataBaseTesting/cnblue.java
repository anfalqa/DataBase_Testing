package dataBaseTesting;

import java.sql.*;

import org.testng.Assert;
import org.testng.annotations.*;

public class cnblue {
	Connection con = null;
	Statement stm = null;
	ResultSet rs;

	@BeforeTest
	public void setup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root");
	}

	@Test()
	public void InsertQuery() throws SQLException {
		stm = con.createStatement();

		String InsertQuery = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city,country )"
				+ "VALUES (44446, 'passTest COMPANY.', 'IBRAHEM', 'MOHAMAD', '000000000', 'Amman', 'Amman' ,'Jordan');";

		int rowEffected = stm.executeUpdate(InsertQuery);
		System.out.println("insert = " + rowEffected);
		Assert.assertEquals(rowEffected > 0, true, "Error !!! ");
	}

	@Test()
	public void SelectQuery() throws SQLException {
		stm = con.createStatement();
		rs = stm.executeQuery("SELECT * FROM customers where customerNumber=44446;");

		while (rs.next()) {
			String thecustomerName = rs.getString("customerName");
			String thecustomerNumber = rs.getString("customerNumber");
			String customerFirstName = rs.getString("contactFirstName");
			String customerLastName = rs.getString("contactLastName");
			String customerCITY = rs.getString("city");

			System.out.println(thecustomerName);
			System.out.println(thecustomerNumber);
			System.out.println(customerFirstName);

			System.out.println(customerLastName);

			System.out.println(customerCITY);

			Assert.assertEquals(customerLastName, "IBRAHEM", "Error!! the customer lastname is not matching");

		}

	}

	@Test()
	public void UpdateQuery() throws SQLException {
		stm = con.createStatement();
		String updateQuery = "UPDATE customers SET city = 'Irbid' WHERE customerNumber = 44446;";
		int rowEffected = stm.executeUpdate(updateQuery);
		System.out.println("update = " + rowEffected);
		Assert.assertEquals(rowEffected > 0, true, "Error !!! ");

	}

	@Test()
	public void DeleteQuery() throws SQLException {
		stm = con.createStatement();
		String deleteQuery = "DELETE FROM CUSTOMERS WHERE customerNumber = 44446;";
		int rowEffected = stm.executeUpdate(deleteQuery);
		System.out.println("delete = " + rowEffected);
		Assert.assertEquals(rowEffected > 0, true, "Error !!! ");

	}

}// end of the class
