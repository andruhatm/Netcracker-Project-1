package repo.xml;

import entities.Contract;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import repo.ContractRepo;
import repo.db.JDBCWorker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Class describes work with XML
 *
 * @author Maksim Shcherbakov
 * @version 1.0
 */
public class XMLRepo {

	/**
	 * Logger for the work with XML
	 */
	private static final Logger logger = LogManager.getLogger(JDBCWorker.class);

	/**
	 * Method saving contracts repository in XML
	 *
	 * @param contractRepository contracts repository for saving
	 */
	public void saveContracts(ContractRepo contractRepository, String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(contractRepository.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(contractRepository, new File(fileName));
			logger.info("Repository " + contractRepository + " saved");
		} catch (JAXBException exception) {
			logger.error("Error: Saving contacts repository in XML failed", exception);
			exception.printStackTrace();
		}
	}

	/**
	 * Method getting XML dump in contacts repository
	 *
	 * @param contractRepository contacts repository for saving dump
	 */
	public void dump(ContractRepo contractRepository, String fileName) {
		try {
			JAXBContext context = JAXBContext.newInstance(contractRepository.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			FileInputStream fileInputStream = new FileInputStream(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
			ContractRepo repo = ((ContractRepo) unmarshaller.unmarshal(inputStreamReader));

			for (int i=0; i< repo.getLength(); i++) {
				contractRepository.add(repo.getById(i));
			}

			logger.info("Success");
		} catch (FileNotFoundException | JAXBException exception) {
			logger.error("Failed get repository from xml", exception);
			exception.printStackTrace();
		}
	}
}
