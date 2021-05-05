import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class CarPlan extends Plan {
    static final String inputTag = "CAR_PLAN";
    RangeCriterion mileageCriterion = new RangeCriterion();

    CarPlan(HashMap<String, ArrayList<Tag>> tags) {
        super(tags);
        //if a key called CAR.MILEAGE exists
        if (tags.get("CAR.MILEAGE") != null) {
            //for all tags of arraylist with key CAR.MILEAGE
            for (Tag tag : tags.get("CAR.MILEAGE")) {
                //call addCriterion with the given tag
                mileageCriterion.addCriterion(tag);
            }
        }
    }

    @Override
    boolean isEligible(Insurable insurable, Date date) {
        //if the Insurable object is not of subclass Car
        if (!(insurable instanceof Car))
            return false;
        //cast insurable to become a Car object
        Car car = (Car) insurable;
        //check if the car's mileage is in range, and return the boolean
        return mileageCriterion.isInRange(car.getMileage());
    }

    //getter function overridden to specifically get the car object
    @Override
    Insurable getInsuredItem(Customer customer, Database database) {
        return database.getCar(customer.getName());
    }
}
