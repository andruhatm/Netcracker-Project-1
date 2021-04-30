package main;

import org.jetbrains.annotations.NotNull;
import repo.ContractRepo;
import repo.builders.CsvContractRepoBuilder;
import repo.injection.Injector;

/**
 * @author andruha.tm
 * @version 1.0
 */
public class Main {

	/**
	 * Starting mthd of an application
	 *
	 * @param args of cmd line
	 */
	public static void main(@NotNull final String[] args) throws IllegalAccessException {
		Injector injector = new Injector();

		CsvContractRepoBuilder builder = injector.inject(new CsvContractRepoBuilder());
		ContractRepo repo = injector.inject(new ContractRepo());
		ContractRepo contractRepo = builder.build("src/main/resources/netcracker lab3.csv");

		repo.outputRepo();
	}
}
