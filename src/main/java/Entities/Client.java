package Entities;

import Entities.enums.Sex;
import java.time.LocalDate;
import java.time.Period;

/**
 * Entity-class of Client
 * @author andruha.tm
 * @version 1.0
 */
public class Client {
	/**
	 * Client id
	 */
	private int id;
	/**
	 * Client's name
	 */
	private String name;
	/**
	 * Client's surname
	 */
	private String surname;
	/**
	 * Client's patranymic
	 */
	private String patronymic;
	/**
	 * Client's date of birth
	 */
	private LocalDate dateOfBirth;
	/**
	 * Client's sex
	 */
	private Sex sex;
	/**
	 * Client's passport serie
	 */
	private int passportSeries;
	/**
	 * Client's passport id
	 */
	private int passportId;

	/**
	 * Constructor - creates new instance of Client
	 * @param id unique identifier
	 * @param name name
	 * @param surname surname
	 * @param patronymic patronymic
	 * @param dateOfBirth date of birth (yyyy-mm-dd)
	 * @param sex sex
	 * @param passportSeries passport series
	 * @param passportId passport id
	 */
	public Client(int id, String name, String surname, String patronymic, LocalDate dateOfBirth, Sex sex, int passportSeries, int passportId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.passportSeries = passportSeries;
		this.passportId = passportId;
	}

	/**
	 * mthd for identifying Client's age using {@link Client#dateOfBirth}
	 * @return Client's age
	 */
	public int getAge(){
		return Period.between(dateOfBirth,LocalDate.now()).getYears();
	}

	/**
	 * mthd for getting full client's passport info
	 * @return full passport info
	 */
	public String getPassport(){return String.valueOf(getPassportSeries());}

	/**
	 * mthd for getting value of {@link Client#id}
	 * @return Client's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * mthd for setting value of {@link Client#id}
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * mthd for getting value of {@link Client#name}
	 * @return Client's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * mthd for setting value of {@link Client#name}
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * mthd for getting value of {@link Client#surname}
	 * @return Client's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * mthd for setting value of {@link Client#surname}
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * mthd for getting value of {@link Client#patronymic}
	 * @return Client's patronymic
	 */
	public String getPatronymic() {
		return patronymic;
	}

	/**
	 * mthd for setting value of {@link Client#patronymic}
	 * @param patronymic
	 */
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	/**
	 * mthd for getting value of {@link Client#dateOfBirth}
	 * @return Client's date of birth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * mthd for setting value of {@link Client#dateOfBirth}
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * mthd for getting value of {@link Client#sex}
	 * @return Client's sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * mthd for setting value of {@link Client#sex}
	 * @param sex
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/**
	 * mthd for getting value of {@link Client#passportSeries}
	 * @return Client's passportSeries
	 */
	public int getPassportSeries() {
		return passportSeries;
	}

	/**
	 * mthd for setting value of {@link Client#passportSeries}
	 * @param passportSeries
	 */
	public void setPassportSeries(int passportSeries) {
		this.passportSeries = passportSeries;
	}

	/**
	 * mthd for getting value of {@link Client#passportId}
	 * @return Client's passportId
	 */
	public int getPassportId() {
		return passportId;
	}

	/**
	 * mthd for setting value of {@link Client#passportId}
	 * @param passportId
	 */
	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}

	@Override
	public String toString() {
		return "Client{" +
						"id=" + id +
						", name='" + name + '\'' +
						", surname='" + surname + '\'' +
						", patronymic='" + patronymic + '\'' +
						", dateOfBirth=" + dateOfBirth +
						", sex=" + sex +
						", passportSeries=" + passportSeries +
						", passportId=" + passportId +
						'}';
	}
}

