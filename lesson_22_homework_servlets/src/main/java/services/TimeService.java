package services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeService {
    public String get() {
        String result = null;
        LocalTime localTime = java.time.LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        result = dtf.format(localTime);
        return result;
    }
}
