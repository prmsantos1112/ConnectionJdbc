package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dbase.DBASE;
import dbase.DbException;

public class ProgramTransactionAcid {

	public static void main(String[] args) {

		Connection connect = null;
		Statement prepared = null;

		try {
			connect = DBASE.getConnection();

			connect.setAutoCommit(false);

			prepared = connect.createStatement();

			int rows1 = prepared.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			// int x = 1;
			// if (x < 2) {
				// throw new SQLException("Fake Error !!");
			// }

			int rows2 = prepared.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			connect.commit();

			System.out.println("Rows1 " + rows1);
			System.out.println("Rows2 " + rows2);

			/*
			 * prepared = connect.prepareStatement( "DELETE FROM department WHERE Id = ?");
			 * 
			 * prepared.setInt(1, 5);
			 * 
			 * int rowsAffected = prepared.executeUpdate();
			 * 
			 * System.out.println("Done !! Rows Affected: " + rowsAffected);
			 */

		} catch (SQLException e) {
			try {
				connect.rollback();
				throw new DbException("Transaction Rolled Back !! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error Trying to Rollback !! Caused by: " + e.getMessage());
			}

		} finally {
			DBASE.closeStatement(prepared);
			DBASE.closeConnection();
		}

	}

}
