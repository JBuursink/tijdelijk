package nl.hu.ipass.webapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresBaseDao {
	protected final Connection getConnection() {
		Connection result = null;

		try {
			Class.forName("org.postgresql.Driver");
			result = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ipass", "postgres", "postgres");
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return result;
	}

}
