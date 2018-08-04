package application.cdms.component.data.handler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class CustomeStringConverter extends StringConverter<LocalDate>{

	private static final DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	@Override
	public LocalDate fromString(String dateString) {
		if(dateString==null || dateString.trim().isEmpty())
        {
            return null;
        }
        return LocalDate.parse(dateString,dateTimeFormatter);
	}

	@Override
	public String toString(LocalDate localDate) {
		if(localDate==null)
            return "";
        return dateTimeFormatter.format(localDate);
	}

}
