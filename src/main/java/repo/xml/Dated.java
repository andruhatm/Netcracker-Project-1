package repo.xml;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * marshal/unmarshal LocalDate
 */
@XmlType
public class Dated {
    /**
     * Field for saving value
     */
    @XmlAttribute(name="value")
    public String value;
}
