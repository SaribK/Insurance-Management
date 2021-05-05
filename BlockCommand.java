import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

class BlockCommand extends Command {
    private String blockType;
    private HashMap<String, ArrayList<Tag>> tags = new HashMap<>();

    //initialize object
    BlockCommand(String blockType) {
        this.blockType = blockType;
    }

    void addTag(Tag tag) {
        //if the given tag does not exist
        if (!tags.containsKey(tag.getName())) {
            //create a new arraylist with that tag name
            tags.put(tag.getName(), new ArrayList<Tag> ());
        }
        //add the tag to the arraylist with the tag name
        tags.get(tag.getName()).add(tag);
    }

    String getBlockType() {
        return blockType;
    }

    //overridden function
    @Override
    void run(Database database) throws ParseException {
        //depending on the blockType, a new object is created
        //e.g if blockType is "CUSTOMER"
        if (blockType.equals(Customer.inputTag)) {
            //create a new customer
            database.insertCustomer(new Customer(tags));
        } if (blockType.equals(Home.inputTag)) {
            database.insertHome(new Home(tags));
        } if (blockType.equals(Car.inputTag)) {
            database.insertCar(new Car(tags));
        } if (blockType.equals(Claim.inputTag)) {
            Claim claim = new Claim(tags);
            database.insertClaim(claim);
            //if the claim is valid
            if (processClaim(claim, database)) {
                //declare claim successful
                claim.setSuccessful(true);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was successful.");
            }
            //if claim is invalid
            else {
                //declare clain unsuccessful
                claim.setSuccessful(false);
                System.out.println("Claim on " + Utils.formattedDate(claim.getDate())
                        + " for contract " + claim.getContractName() + " was not successful.");
            }
        } if (blockType.equals(Contract.inputTag)) {
            database.insertContract(new Contract(tags));
        } if (blockType.equals(HomePlan.inputTag)) {
            database.insertPlan(new HomePlan(tags));
        } if (blockType.equals(CarPlan.inputTag)) {
            database.insertPlan(new CarPlan(tags));
        }
    }

    private boolean processClaim(Claim claim, Database database) {
        //get contract, customer, plan using database getter functions
        Contract contract = database.getContract(claim.getContractName());
        Customer customer = database.getCustomer(contract.getCustomerName());
        Plan plan = database.getPlan(contract.getPlanName());
        //get insurable using Plan's getter function
        Insurable insurable = plan.getInsuredItem(customer, database);

        // If the claimed amount is higher than covered by the plan.
        if (plan.getMaxCoveragePerClaim() < claim.getAmount())
            return false;

        // If the claim date is not in the contract period.
        if (claim.getDate().after(contract.getEndDate()) || claim.getDate().before(contract.getStartDate()))
            return false;

        // If the customer was not eligible.
        if (!plan.isEligible(customer, claim.getDate()))
            return false;

        return plan.isEligible(insurable, claim.getDate());
    }
}
