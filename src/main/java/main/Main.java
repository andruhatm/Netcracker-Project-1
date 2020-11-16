package main;

import org.jetbrains.annotations.NotNull;
import repo.ContractRepo;
import repo.builders.CsvContractRepoBuilder;

/**
 * @author andruha.tm
 * @version 1.0
 */
public class Main {

	/**
	 * Starting mthd of an application
	 * @param args of cmd line
	 */
	public static void main(@NotNull String[] args) {
		ContractRepo repo = new CsvContractRepoBuilder().build("src/main/resources/netcracker lab3.csv");
		repo.outputRepo();
	}
}
