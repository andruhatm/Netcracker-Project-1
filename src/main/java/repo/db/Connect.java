package repo.db;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * db connection builder class
 *
 * @author andruha.tm
 * @version 1.0
 */
public class Connect {

	/**
	 * logger instance
	 */
	private static final Logger logger = LoggerFactory.getLogger(Connect.class);

	/**
	 * Connection instance to share
	 */
	public static Connection connection = null;

	/**
	 * connection builder mthd
	 *
	 * @return connection instance
	 */
	public static Connection connect() {
		final String URL = "jdbc:postgresql://localhost:5432/contracts";
		final String username = "postgres";
		final String password = "123";

		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, username, password);
			logger.debug("Успешное соединение с БД");
		} catch (ClassNotFoundException | SQLException e) {
			logger.debug("Нет коннекта к базе: " + e);
		}
		return connection;
	}

	/**
	 * connection getter mthd
	 *
	 * @return connection obj
	 */
	public static Connection getConnection() {
		return connection;
	}
}

