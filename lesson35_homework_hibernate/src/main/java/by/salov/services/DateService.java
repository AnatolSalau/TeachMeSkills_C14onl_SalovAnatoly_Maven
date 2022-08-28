package by.salov.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateService {
    public static java.util.Date stringToDateConvert(String dd_MMM_yyyy) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String dateInString = dd_MMM_yyyy;
        Date date = formatter.parse(dateInString);
        return date;
    }
}
