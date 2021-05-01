package repo.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * marshal/unmarshal adapter LocalDate
 */
public class DatedAdapter extends XmlAdapter<Dated, LocalDate> {

    /**
     * Method marshalling LocalDate in Dated
     * @param localDate date for marshalling
     * @return localDate with Dated type
     */
    @Override
    public Dated marshal(LocalDate localDate) {
        Dated dated = new Dated();
        dated.value = String.valueOf(localDate);
        return dated;
    }

    /**
     * Method unmarshalling Dated in LocalDate
     * @param dated date to unmarshall
     * @return localDate of LocalDate type
     */
    @Override
    public LocalDate unmarshal(Dated dated) {
        return LocalDate.parse(dated.value);
    }
}
