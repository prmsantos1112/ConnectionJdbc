package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbase.DBASE;
import dbase.DbIntegrityException;

public class ProgramDeletarDados {

	public static void main(String[] args) {
		
		Connection connect = null;
		PreparedStatement prepared = null;
		
		try { 
			connect = DBASE.getConnection();
			
			prepared = connect.prepareStatement(
					"DELETE FROM department WHERE Id = ?");
			
			prepared.setInt(1, 5);
			
			int rowsAffected = prepared.executeUpdate();
			
			System.out.println("Done !! Rows Affected: " + rowsAffected);
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
			
		}
		finally {
			DBASE.closeStatement(prepared);
			DBASE.closeConnection();
		}

	}

}
