import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class HomePlan extends Plan {
    static final String inputTag = "HOME_PLAN";
    private RangeCriterion homeValueCriterion = new RangeCriterion();
    private RangeCriterion homeAgeCriterion = new RangeCriterion();

    HomePlan(HashMap<String, ArrayList<Tag>> tags) {
        super(tags);
        //if a key called HOME.VALUE exists
        if (tags.get("HOME.VALUE") != null) {
            //for all tags of arraylist with key HOME.VALUE
            for (Tag tag : tags.get("HOME.VALUE")) {
                //call addCriterion with the given tag
                homeValueCriterion.addCriterion(tag);
            }
        }
        //same procedure as the one for HOME.VALUE
        if (tags.get("HOME.AGE") != null) {
            for (Tag tag : tags.get("HOME.AGE")) {
                homeAgeCriterion.addCriterion(tag);
            }
        }
    }

    @Override
    boolean isEligible(Insurable insurable, Date date) {
        //make sure Insurable object is of the Home subclass
        if (!(insurable instanceof Home))
            return false;
        //cast to make insurable of type Home
        Home home = (Home) insurable;
        //check if the car's value is in range, and return the boolean
        if (!homeValueCriterion.isInRange(home.getValue()))
            return false;

        // Extracting the approximate age of the home (calendar years)
        LocalDate localCurrentDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localBuiltDate = home.getBuildDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long age = localCurrentDate.getYear() - localBuiltDate.getYear();;
        // Checking if the age is in the range.
        return homeAgeCriterion.isInRange(age);
    }

    //getter function
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getHome(customer.getName());
    }

}
