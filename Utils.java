import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

class Utils {

    //creates SimpleDateFormat to parse it into a Date format
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    //creates date instance
    static Date convertDate(String input) throws ParseException {
        return formatter.parse(input);
    }
    //creates string version of the date
    static String formattedDate(Date date) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }
}
