package repo.db.dao;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import entities.Client;
import entities.enums.Sex;
import repo.db.Connect;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Client dao impl
 *
 * @author andruha.tm
 */
public class ClientDAO implements DAO<Client> {

	/**
	 * Connection obj
	 */
	final Connection connection;

	/**
	 * logger instance
	 */
	final Logger logger = LoggerFactory.getLogger(ClientDAO.class);

	/**
	 * dao constructor
	 *
	 * @param connection connection obj to use
	 */
	public ClientDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * save client entity to db
	 *
	 * @param entity entity to save
	 * @return boolean success value
	 */
	@Override
	public boolean save(Client entity) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO public.clients(" +
											"id, surname, first_name, patronymic, date_of_birth, gender, number_passport, series_passport)" +
											"VALUES (?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setInt(1, entity.getId());
			preparedStatement.setString(2, entity.getSurname());
			preparedStatement.setString(3, entity.getName());
			preparedStatement.setString(4, entity.getPatronymic());
			preparedStatement.setDate(5, Date.valueOf(entity.getDateOfBirth()));
			preparedStatement.setString(6, entity.getSex().toString());
			preparedStatement.setInt(7, entity.getPassportId());
			preparedStatement.setInt(8, entity.getPassportSeries());

			preparedStatement.executeUpdate();

			logger.debug("Client" + entity.toString() + " saved");
		} catch (SQLException exception) {
			logger.error("Error during saving", exception);
			exception.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * checks client entity existence by id
	 *
	 * @param id client id
	 * @return boolean value
	 */
	@Override
	public boolean get(int id) {
		Integer clientId = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.* from public.clients c where c.id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				clientId = resultSet.getInt(1);
			}
			logger.debug("Client exists");
		} catch (SQLException exception) {
			logger.error("client doesnt exist", exception);
			exception.printStackTrace();
		}
		return clientId == null;
	}

	/**
	 * getting client from db by id
	 *
	 * @param clientId id of client to get
	 * @return Client obj
	 */
	public Client getById(Integer clientId) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.* from public.clients c where c.id = ?");
			preparedStatement.setInt(1, clientId);
			ResultSet resultSetClient = preparedStatement.executeQuery();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			while (resultSetClient.next()) {
				return new Client(
								resultSetClient.getInt(1),
								resultSetClient.getString(2),
								resultSetClient.getString(3),
								resultSetClient.getString(4),
								LocalDate.parse(String.valueOf(resultSetClient.getDate(5)), formatter),
								Sex.valueOf(resultSetClient.getString(6)),
								resultSetClient.getInt(7),
								resultSetClient.getInt(8));
			}
		} catch (SQLException exception) {
			logger.error("Error getting client", exception);
			exception.printStackTrace();
		}
		return null;
	}
}
