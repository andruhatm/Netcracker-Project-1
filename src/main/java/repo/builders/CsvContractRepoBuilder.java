package repo.builders;

import Entities.Client;
import Entities.Contract;
import Entities.contracts.InternetContract;
import Entities.contracts.MobileContract;
import Entities.contracts.TVContract;
import Entities.enums.Package;
import Entities.enums.Sex;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import repo.ContractRepo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CsvContractRepoBuilder {

	private final ContractRepo repo = new ContractRepo();

	private ArrayList<Client> clients = new ArrayList<>();

	public final ContractRepo build(String path){
		try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {

		CSVParser parser = new CSVParserBuilder()
						.withSeparator(';')
						.withIgnoreQuotations(true)
						.build();
		CSVReader csvReader = new CSVReaderBuilder(reader)
						.withSkipLines(0)
						.withCSVParser(parser)
						.build();

			String[] line;
			int pointer =0;
			while((line = csvReader.readNext()) != null){
				if(pointer == 0){
					line[0] = line[0].substring(1);
					pointer++;
				}

				if(repo.checkContractId(Integer.parseInt(line[1]))){
					Client currentClient = null;
					if(checkClient(line[7],line[8])){
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
					}
					else {
						currentClient = getClient(line[7],line[8]);
					}

					String[] startDate = line[2].split("-");
					String[] endDate = line[3].split("-");

					switch (line[0].replaceAll(" ","")){
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
							repo.add(contract);
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
							repo.add(contract1);
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
							repo.add(contract2);
							break;
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return repo;
	}

	/**
	 * mthd for getting Client with recieved passport info
	 * @param id passport series
	 * @param number passport number
	 * @return Client's obj
	 */
	private Client getClient(String id,String number) {
		Client client1 = null;
		for(Client client: clients){
			if (client.getPassportSeries() == Integer.parseInt(id) && client.getPassportId() == Integer.parseInt(number)){
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
	private boolean checkClient(String id,String number) {
		boolean check = true;
		for(Client client: clients){
			if (client.getPassportSeries() == Integer.parseInt(id) && client.getPassportId() == Integer.parseInt(number)){
				check = false;
				break;
			}
		}
		//System.out.println("is client unique: "+ check);
		return check;
	}

}
