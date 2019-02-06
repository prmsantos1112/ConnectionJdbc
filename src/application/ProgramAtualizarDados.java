package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbase.DBASE;

public class ProgramAtualizarDados {

	public static void main(String[] args) {
		
		Connection connect = null;
		PreparedStatement prepared = null;
		
		try { 
			connect = DBASE.getConnection();
			
			prepared = connect.prepareStatement(
					"UPDATE seller SET BaseSalary = BaseSalary + ? WHERE"
					+ "(DepartmentId = ?)");
			
			prepared.setDouble(1, 500.0);
			prepared.setInt(2, 2);
			
			int rowsAffected = prepared.executeUpdate();
			
			System.out.println("Done !! Rows Affected: " + rowsAffected);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			DBASE.closeStatement(prepared);
			DBASE.closeConnection();
		}

	}

}
