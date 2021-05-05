import java.util.ArrayList;
import java.util.HashMap;

class Insurable {
    protected String ownerName;
    protected long value;

    Insurable(HashMap<String, ArrayList<Tag>> tags) {
        //gets ArrayList of owner names with key "OWNER_NAME"
        // and sets ownerName to its first element's value
        ownerName = tags.get("OWNER_NAME").get(0).getValue();
        //String value is converted to long with parseLong
        value = Long.parseLong(tags.get("VALUE").get(0).getValue());
    }

    //getter functions for accessing this class's variables
    public String getOwnerName() {
        return ownerName;
    }

    public long getValue() {
        return value;
    }
}
