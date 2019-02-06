package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import dbase.DBASE;

public class ProgramInserirDadosRetornoId {

	public static void main(String[] args) {

		SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
		Connection connection = null;
		PreparedStatement prepared = null;

		try {
			connection = DBASE.getConnection();

			prepared = connection
					.prepareStatement("Insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) "
							+ "Values" + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			prepared.setString(1, "Evonira Santos");
			prepared.setString(2, "evosimoes@gmail.com");
			prepared.setDate(3, new java.sql.Date(simpleDate.parse("19/05/1969").getTime()));
			prepared.setDouble(4, 4000.0);
			prepared.setInt(5, 4);

			int rowsAffected = prepared.executeUpdate();

			// System.out.println("Done !! Rows affected: " + rowsAffected);

			if (rowsAffected > 0) {
				ResultSet result = prepared.getGeneratedKeys();
				while (result.next()) {
					int id = result.getInt(1);
					System.out.println("Done ! Id = " + id);
				}

			} else {
				System.out.println("No Rown Affected !! ");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ParseException e) {
			e.printStackTrace();
			
		} finally {
			DBASE.closeStatement(prepared);
			DBASE.closeConnection();
		}

	}

}
