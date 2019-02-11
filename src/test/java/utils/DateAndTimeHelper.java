package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTimeHelper {

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getFutureDaysInString(int nummOfdaysInFuture){
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(nummOfdaysInFuture);
        return dateFormat.format(localDateTime);
    }

    public String getPastDaysInString(int nummOfdaysInPast){
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(nummOfdaysInPast);
        return dateFormat.format(localDateTime);
    }
}
