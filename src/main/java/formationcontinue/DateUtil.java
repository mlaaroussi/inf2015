package formationcontinue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static DateUtil DATE_UTIL = new DateUtil();
    private DateFormat formatter;

    private DateUtil() {
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
    }

    public static boolean estDateValide(String date) {
        try {
            getDate(date);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static Date getDate(String date) {
        try {
            return DATE_UTIL.formatter.parse(date);
        } catch (ParseException ex) {
            throw new IllegalArgumentException();
        }
    }
}
