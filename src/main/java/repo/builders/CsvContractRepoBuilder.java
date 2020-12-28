package repo.builders;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import entities.Client;
import entities.Contract;
import entities.contracts.InternetContract;
import entities.contracts.MobileContract;
import entities.contracts.TVContract;
import entities.enums.Package;
import entities.enums.Sex;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import repo.ContractRepo;
import repo.validators.ValidateStatus;
import repo.validators.Validator;
import repo.validators.impl.base.AgeValidator;
import repo.validators.impl.base.ContractPeriodValidator;
import repo.validators.impl.base.FioValidator;
import repo.validators.impl.base.PassportValidator;
import repo.validators.impl.internet.DataCountValidator;
import repo.validators.impl.mobile.TrafficDataValidator;

/**
 * RepoBuilderClass
 * @author andruha.tm
 * @version 1.0
 */
public class CsvContractRepoBuilder {
	static final Logger logger = Logger.getLogger(CsvContractRepoBuilder.class);

	/**
	 * repo filed to return
	 */
	private final ContractRepo repo = new ContractRepo();

	/**
	 * clients list field to check uniqueness
	 */
	private final ArrayList<Client> clients = new ArrayList<>();

	/**
	 * validator list field
	 */
	private static final List<Validator<Contract>> validators = new ArrayList<>();

	static {
		validators.add(new ContractPeriodValidator());
		validators.add(new FioValidator());
		validators.add(new AgeValidator());
		validators.add(new PassportValidator());
		validators.add(new DataCountValidator());
		validators.add(new TrafficDataValidator());
	}

	/**
	 * builds repo from file by {@link CSVParser} and {@link CSVReader}
	 * @param path file to build from
	 * @return ready repo
	 */
	public final ContractRepo build(final String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
			CSVParser parser = new CSVParserBuilder()
				.withSeparator(';')
				.withIgnoreQuotations(true)
				.build();
			CSVReader csvReader = new CSVReaderBuilder(reader)
				.withSkipLines(0)
				.withCSVParser(parser)
				.build();

			String[] line;
			int pointer = 0;
			while ((line = csvReader.readNext()) != null) {
				if (pointer == 0) {
					line[0] = line[0].substring(1);
					pointer++;
				}

				if (repo.checkContractId(Integer.parseInt(line[1]))) {
					logger.debug("Contract with such id already exists");
					Client currentClient = null;
					if (checkClient(line[7], line[8])) {
						logger.debug("Creating new Client Instance");
						String[] fio = line[4].split(" ");
						String[] dateOfBirth = line[5].split("-");
						Client client = new Client(
							clients.size(),
							fio[0],
							fio[1],
							fio[2],
							LocalDate.of(
								Integer.parseInt(dateOfBirth[2]),
								Integer.parseInt(dateOfBirth[1]),
								Integer.parseInt(dateOfBirth[0])
							),
							Sex.valueOf(line[6]),
							Integer.parseInt(line[7]),
							Integer.parseInt(line[8])
						);
						currentClient = client;
						clients.add(client);
					} else {
						currentClient = getClient(line[7], line[8]);
					}

					String[] startDate = line[2].split("-");
					String[] endDate = line[3].split("-");

					switch (line[0].replaceAll(" ", "")) {
						case "mobile":
							String[] specs = line[9].split(",");
							Contract contract = new MobileContract(
								Integer.parseInt(line[1]),
								LocalDate.of(
									Integer.parseInt(startDate[2]),
									Integer.parseInt(startDate[1]),
									Integer.parseInt(startDate[0])
								),
								LocalDate.of(
									Integer.parseInt(endDate[2]),
									Integer.parseInt(endDate[1]),
									Integer.parseInt(endDate[0])
								),
								currentClient,
								Integer.parseInt(specs[0]),
								Integer.parseInt(specs[1]),
								Float.parseFloat(specs[2])
							);
							if (doValidate(contract)) {
								repo.add(contract);
							}
							break;
						case "tv":
							Contract contract1 = new TVContract(
								Integer.parseInt(line[1]),
								LocalDate.of(
									Integer.parseInt(startDate[2]),
									Integer.parseInt(startDate[1]),
									Integer.parseInt(startDate[0])
								),
								LocalDate.of(
									Integer.parseInt(endDate[2]),
									Integer.parseInt(endDate[1]),
									Integer.parseInt(endDate[0])
								),
								currentClient,
								Package.valueOf(line[9])
							);

							if (doValidate(contract1)) {
								repo.add(contract1);
							}
							break;
						case "ethernet":
							Contract contract2 = new InternetContract(
								Integer.parseInt(line[1]),
								LocalDate.of(
									Integer.parseInt(startDate[2]),
									Integer.parseInt(startDate[1]),
									Integer.parseInt(startDate[0])
								),
								LocalDate.of(
									Integer.parseInt(endDate[2]),
									Integer.parseInt(endDate[1]),
									Integer.parseInt(endDate[0])
								),
								currentClient,
								Double.parseDouble(line[9])
							);

							if (doValidate(contract2)) {
								repo.add(contract2);
							}
							break;
						default:
							break;
					}
				}
			}
		} catch (IOException e) {
			logger.fatal("Error opening file", e);
			e.printStackTrace();
		}

		return repo;
	}

	/**
	 * streams contract throw validators to check for warnings
	 * @param contract contract ready to add in repo
	 * @return bool success value
	 */
	public boolean doValidate(final Contract contract) {
		//		List<Message> messages = validators.stream()
		//						.filter(v -> v.getAppliableClass().isInstance(contract.getClass()))
		//						.map(v -> v.validate(contract))
		//						.collect(Collectors.toList());

		for (Validator<Contract> validator : validators) {
			if (validator.getAppliableClass().equals(contract.getClass())) {
				if (validator.validate(contract).getStatus() == (ValidateStatus.WARNING)) {
					logger.warn("Contract applicable to Contract.class didn't pass validation");
					return false;
				}
			} else if (validator.getAppliableClass().equals(Contract.class)) {
				if (validator.validate(contract).getStatus() == (ValidateStatus.WARNING)) {
					logger.warn(
						"Contract appliabel to ? extends Contract.class didn't pass validation"
					);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * mthd for getting Client with recieved passport info
	 * @param id passport series
	 * @param number passport number
	 * @return Client's obj
	 */
	private Client getClient(final String id, final String number) {
		Client client1 = null;
		for (Client client : clients) {
			if (
				client.getPassportSeries() == Integer.parseInt(id)
								&& client.getPassportId() == Integer.parseInt(number)
			) {
				client1 = client;
				break;
			}
		}
		return client1;
	}

	/**
	 * mthd for checking client uniqueness by passport
	 * @param id passport series
	 * @param number passport number
	 * @return true if no such client in repo, otherwise false
	 */
	private boolean checkClient(final String id, final String number) {
		boolean check = true;
		for (Client client : clients) {
			if (
				client.getPassportSeries() == Integer.parseInt(id)
								&& client.getPassportId() == Integer.parseInt(number)
			) {
				check = false;
				break;
			}
		}
		//System.out.println("is client unique: "+ check);
		return check;
	}
}
