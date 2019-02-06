package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dbase.DBASE;

public class ProgramInserirDados {

	public static void main(String[] args) {
		
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		PreparedStatement prepared = null;
		
		try {
			connection = DBASE.getConnection();
			
			prepared = connection.prepareStatement(
					"Insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " 
					+ "Values"
					+"(?, ?, ?, ?, ?)");
			
			prepared.setString(1, "Ricardo Augusto");
			prepared.setString(2, "ra_guto@gmail.com");
			prepared.setDate(3, new java.sql.Date(simpleDate.parse("03/08/1993").getTime()));
			prepared.setDouble(4, 3000.0);
			prepared.setInt(5, 4);
			
			int rowsAffected = prepared.executeUpdate();
			
			System.out.println("Done !! Rows affected: " + rowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DBASE.closeStatement(prepared);
			DBASE.closeConnection();
		}

	}

}
