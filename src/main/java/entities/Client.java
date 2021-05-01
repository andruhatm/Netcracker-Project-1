package entities;

import entities.enums.Sex;
import repo.xml.DatedAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.Period;

/**
 * Entity-class of Client
 *
 * @author andruha.tm
 * @version 1.0
 */
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
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
	@XmlJavaTypeAdapter(DatedAdapter.class)
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
	 * no args constructor
	 */
	public Client() {
	}

	/**
	 * Constructor - creates new instance of Client
	 *
	 * @param id             unique identifier
	 * @param name           name
	 * @param surname        surname
	 * @param patronymic     patronymic
	 * @param dateOfBirth    date of birth (yyyy-mm-dd)
	 * @param sex            sex
	 * @param passportSeries passport series
	 * @param passportId     passport id
	 */
	public Client(
					final int id,
					final String name,
					final String surname,
					final String patronymic,
					final LocalDate dateOfBirth,
					final Sex sex,
					final int passportSeries,
					final int passportId
	) {
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
	 *
	 * @return Client's age
	 */
	public int getAge() {
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	/**
	 * mthd for getting full client's passport info
	 *
	 * @return full passport info
	 */
	public String getPassport() {
		return String.valueOf(getPassportSeries());
	}

	/**
	 * mthd for getting value of {@link Client#id}
	 *
	 * @return Client's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * mthd for setting value of {@link Client#id}
	 *
	 * @param id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * mthd for getting value of {@link Client#name}
	 *
	 * @return Client's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * mthd for setting value of {@link Client#name}
	 *
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * mthd for getting value of {@link Client#surname}
	 *
	 * @return Client's surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * mthd for setting value of {@link Client#surname}
	 *
	 * @param surname
	 */
	public void setSurname(final String surname) {
		this.surname = surname;
	}

	/**
	 * mthd for getting value of {@link Client#patronymic}
	 *
	 * @return Client's patronymic
	 */
	public String getPatronymic() {
		return patronymic;
	}

	/**
	 * mthd for setting value of {@link Client#patronymic}
	 *
	 * @param patronymic
	 */
	public void setPatronymic(final String patronymic) {
		this.patronymic = patronymic;
	}

	/**
	 * mthd for getting value of {@link Client#dateOfBirth}
	 *
	 * @return Client's date of birth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * mthd for setting value of {@link Client#dateOfBirth}
	 *
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(final LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * mthd for getting value of {@link Client#sex}
	 *
	 * @return Client's sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * mthd for setting value of {@link Client#sex}
	 *
	 * @param sex
	 */
	public void setSex(final Sex sex) {
		this.sex = sex;
	}

	/**
	 * mthd for getting value of {@link Client#passportSeries}
	 *
	 * @return Client's passportSeries
	 */
	public int getPassportSeries() {
		return passportSeries;
	}

	/**
	 * mthd for setting value of {@link Client#passportSeries}
	 *
	 * @param passportSeries
	 */
	public void setPassportSeries(final int passportSeries) {
		this.passportSeries = passportSeries;
	}

	/**
	 * mthd for getting value of {@link Client#passportId}
	 *
	 * @return Client's passportId
	 */
	public int getPassportId() {
		return passportId;
	}

	/**
	 * mthd for setting value of {@link Client#passportId}
	 *
	 * @param passportId
	 */
	public void setPassportId(final int passportId) {
		this.passportId = passportId;
	}

	/**
	 * toString mthd
	 *
	 * @return Client object values
	 */
	@Override
	public String toString() {
		return (
						"Client{" +
										"id=" +
										id +
										", name='" +
										name +
										'\'' +
										", surname='" +
										surname +
										'\'' +
										", patronymic='" +
										patronymic +
										'\'' +
										", dateOfBirth=" +
										dateOfBirth +
										", sex=" +
										sex +
										", passportSeries=" +
										passportSeries +
										", passportId=" +
										passportId +
										'}'
		);
	}
}
