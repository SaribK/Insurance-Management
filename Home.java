import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class Home extends Insurable {
    private String postalCode;
    private Date buildDate;

    static final String inputTag = "HOME";

    Home(HashMap<String, ArrayList<Tag>> tags) throws ParseException {
        //initialize inherited fields
        super(tags);
        //gets arraylist with the key "POSTAL_CODE"
        // and sets postalCode to the value of the first element of that list
        postalCode = tags.get("POSTAL_CODE").get(0).getValue();
        //same as postal code, but uses Utils.convertDate to change String to Date
        buildDate = Utils.convertDate(tags.get("BUILD_DATE").get(0).getValue());
    }
    //getter and setter functions for this class's variables
    public String getPostalCode() {
        return postalCode;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public static String getInputTag() {
        return inputTag;
    }
}
