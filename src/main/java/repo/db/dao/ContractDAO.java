package repo.db.dao;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import entities.Contract;
import entities.contracts.InternetContract;
import entities.contracts.MobileContract;
import entities.contracts.TVContract;
import repo.db.Connect;

import java.sql.*;

/**
 * Contract dao impl
 *
 * @author andruha.tm
 */
public class ContractDAO implements DAO<Contract> {

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
	public ContractDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * save contract entity to db
	 *
	 * @param entity entity to save
	 * @return boolean success value
	 */
	@Override
	public boolean save(Contract entity) {
		try {
			PreparedStatement preparedStatement = Connect.connect().prepareStatement(
							"INSERT INTO public.contracts(" +
											"id, start_date, end_date, minutes, gb_internet, sms, maximum_speed, package_channel, contract_type, client_id)" +
											"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

			preparedStatement.setInt(1, entity.getId());
			preparedStatement.setDate(2, Date.valueOf(entity.getStartingDate()));
			preparedStatement.setDate(3, Date.valueOf(entity.getFinishingDate()));
			preparedStatement.setInt(10, entity.getClient().getId());
			preparedStatement = setSpecificFields(preparedStatement, entity);

			if (preparedStatement != null) {
				preparedStatement.executeUpdate();
				logger.debug("Contract " + entity + " saved");
			}
		} catch (SQLException exception) {
			logger.error("Error saving contract", exception);
			exception.printStackTrace();
		}
		return false;
	}

	/**
	 * checks contract entity existence by id
	 *
	 * @param id contract id
	 * @return boolean value
	 */
	@Override
	public boolean get(int id) {
		Integer contractId = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT c.* from public.contracts c where c.id = ?");
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				contractId = resultSet.getInt(1);
			}
			logger.debug("Contract exists");
		} catch (SQLException exception) {
			logger.error("Contract doesnt exist", exception);
			exception.printStackTrace();
		}
		return contractId == null;
	}

	/**
	 * building specific ps depends on type of contract
	 *
	 * @param preparedStatement preparedStatement to database
	 * @param contract          contract to check
	 */
	private PreparedStatement setSpecificFields(PreparedStatement preparedStatement, Contract contract) {
		try {
			if (contract instanceof InternetContract) {
				preparedStatement.setNull(4, Types.INTEGER);
				preparedStatement.setNull(5, Types.INTEGER);
				preparedStatement.setNull(6, Types.INTEGER);
				preparedStatement.setDouble(7, ((InternetContract) contract).getMaxSpeed());
				preparedStatement.setNull(8, Types.VARCHAR);
				preparedStatement.setString(9, "ethernet");
				logger.debug("successfully set ethernet fields");
				return preparedStatement;
			} else if (contract instanceof MobileContract) {
				preparedStatement.setInt(4, ((MobileContract) contract).getMinutesCount());
				preparedStatement.setFloat(5, ((MobileContract) contract).getGbCount());
				preparedStatement.setInt(6, ((MobileContract) contract).getSmsCount());
				preparedStatement.setNull(7, Types.INTEGER);
				preparedStatement.setNull(8, Types.VARCHAR);
				preparedStatement.setString(9, "mobile");
				logger.debug("successfully set mobile fields");
				return preparedStatement;
			} else if (contract instanceof TVContract) {
				preparedStatement.setNull(4, Types.INTEGER);
				preparedStatement.setNull(5, Types.INTEGER);
				preparedStatement.setNull(6, Types.INTEGER);
				preparedStatement.setNull(7, Types.INTEGER);
				preparedStatement.setString(8, ((TVContract) contract).getaPackage().toString());
				preparedStatement.setString(9, "tv");
				logger.debug("successfully set tv fields");
				return preparedStatement;
			}
		} catch (SQLException exception) {
			logger.error("error during setting specific contract fields", exception);
			exception.printStackTrace();
		}
		return null;
	}
}
