package repo.db;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import entities.Client;
import entities.Contract;
import entities.contracts.InternetContract;
import entities.contracts.MobileContract;
import entities.contracts.TVContract;
import org.flywaydb.core.Flyway;
import repo.ContractRepo;
import repo.db.dao.ClientDAO;
import repo.db.dao.ContractDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * JDBC utility class
 */
public class JDBCWorker {

	/**
	 * get all contracts sql query
	 */
	private final static String SELECT_CONTRACTS = "SELECT * from contracts";

	/**
	 * logger instance
	 */
	private static final Logger logger = LoggerFactory.getLogger(JDBCWorker.class);

	/**
	 * db initialization mthd
	 */
	public void dbInit() {
		Flyway flyway = Flyway.configure()
						.dataSource("jdbc:postgresql://localhost:5432/contracts", "postgres", "123")
						.locations("db.migration")
						.load();
		flyway.clean();
		flyway.migrate();
	}

	/**
	 * save contracts to db
	 *
	 * @param repository repository to save
	 * @throws SQLException handling error during connection close
	 */
	public void saveContracts(ContractRepo repository) throws SQLException {
		for (Contract contract : repository.getRepo()) {
			if (contract != null) {
				if (checkContract(contract)) {
					if (contract.getClient() != null) {
						saveClient(contract.getClient());
						ContractDAO contractDAO = new ContractDAO(Connect.connect());
						contractDAO.save(contract);
					} else {
						logger.error("Error: client null");
					}
				} else {
					logger.debug("Contract " + contract + " already exists");
				}
			} else {
				logger.error("Error: contract null");
			}
		}
		Connect.getConnection().close();
	}

	/**
	 * Method saving client in database
	 *
	 * @param client client for saving
	 */
	public void saveClient(Client client) {
		if (checkClient(client)) {
			ClientDAO clientDAO = new ClientDAO(Connect.connect());
			clientDAO.save(client);
		} else {
			logger.debug("Client " + client.toString() + " already exists");
		}
	}

	/**
	 * Method checking existences client in database
	 *
	 * @param client client for checking
	 */
	public boolean checkClient(Client client) {
		ClientDAO clientDAO = new ClientDAO(Connect.connect());
		return clientDAO.get(client.getId());
	}

	/**
	 * Method checking existences contract in database
	 *
	 * @param contract contract for checking
	 */
	public boolean checkContract(Contract contract) {
		ContractDAO contractDAO = new ContractDAO(Connect.connect());
		return contractDAO.get(contract.getId());
	}

	/**
	 * Method getting database dump in contacts repository
	 *
	 * @param contractRepository contacts repository to save to
	 * @throws SQLException handling an error when closing a connection
	 */
	public void dump(ContractRepo contractRepository) throws SQLException {
		try {
			PreparedStatement preparedStatement = Connect.connect().prepareStatement(SELECT_CONTRACTS);
			ResultSet resultSetContract = preparedStatement.executeQuery();

			while (resultSetContract.next()) {
				if (Objects.equals(resultSetContract.getString(9), "ethernet")) {
					InternetContract internetContract = new InternetContract(
									resultSetContract.getInt(1),
									String.valueOf(resultSetContract.getDate(2)),
									String.valueOf(resultSetContract.getDate(3)),
									getClient(resultSetContract.getInt(10)),
									resultSetContract.getInt(8)
					);
					contractRepository.add(internetContract);
				} else if (Objects.equals(resultSetContract.getString(9), "mobile")) {
					MobileContract cellularContract = new MobileContract(
									resultSetContract.getInt(1),
									String.valueOf(resultSetContract.getDate(2)),
									String.valueOf(resultSetContract.getDate(3)),
									getClient(resultSetContract.getInt(10)),
									resultSetContract.getInt(5),
									resultSetContract.getInt(6),
									resultSetContract.getInt(7)
					);
					contractRepository.add(cellularContract);
				} else if (Objects.equals(resultSetContract.getString(9), "tv")) {
					TVContract tvContract = new TVContract(
									resultSetContract.getInt(1),
									String.valueOf(resultSetContract.getDate(2)),
									String.valueOf(resultSetContract.getDate(3)),
									getClient(resultSetContract.getInt(10)),
									resultSetContract.getString(8)
					);
					contractRepository.add(tvContract);
				}
			}
		} catch (SQLException exception) {
			logger.error("Error getting dump", exception);
			exception.printStackTrace();
		}
		Connect.getConnection().close();
		logger.debug("Success filling repo with db dump");
	}

	/**
	 * Method getting client by client id
	 *
	 * @param clientId client id for getting client
	 * @return client by client id
	 */
	public Client getClient(final Integer clientId) throws SQLException {
		ClientDAO clientDAO = new ClientDAO(Connect.connect());
		return clientDAO.getById(clientId);
	}
}
