package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dbase.DBASE;

public class ProgramTestConnection {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = DBASE.getConnection();
			statement = connection.createStatement();
			result = statement.executeQuery("select * from department");

			while (result.next()) {
				System.out.println(result.getInt("Id") + ", " + result.getString("Name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {

			DBASE.closeResultSet(result);
			DBASE.closeStatement(statement);
			DBASE.closeConnection();
		}
	}
}