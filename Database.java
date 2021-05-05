import java.util.ArrayList;

class Database {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Home> homes = new ArrayList<>();
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<Plan> plans = new ArrayList<>();
    private ArrayList<Contract> contracts = new ArrayList<>();
    private ArrayList<Claim> claims = new ArrayList<>();
    //add given object to its arraylist of objects
    void insertHome(Home home) {
        homes.add(home);
    }

    void insertCar(Car car) {
        cars.add(car);
    }

    void insertCustomer(Customer customer) { customers.add(customer); }

    void insertPlan(Plan plan) {
        plans.add(plan);
    }

    void insertClaim(Claim claim) {
        claims.add(claim);
    }

    void insertContract(Contract contract) {
        contracts.add(contract);
    }

    Plan getPlan(String name) {
        //go through list of plans
        for (Plan plan : plans) {
            //if the names are equal
            if (plan.name.equals(name))
                //return the Plan object itself
                return plan;
        }
        //if nothing is found return null
        return null;
    }

    //same as getPlan() but goes through arraylist of customers instead
    Customer getCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    //same as getPlan() but with different objects
    Contract getContract(String name) {
        for (Contract contract : contracts) {
            if (contract.getContractName().equals(name))
                return contract;
        }
        return null;
    }

    /**
     * There is at most one home owned by each person.
     */
    //same as getPlan() but with different objects
    Home getHome(String ownnerName) {
        for (Home home : homes) {
            if (home.getOwnerName().equals(ownnerName))
                return home;
        }
        return null;
    }

    /**
     * There is at most one car owned by each person.
     */
    //same as getPlan() but with different objects
    Car getCar(String ownnerName) {
        for (Car car : cars) {
            if (car.getOwnerName().equals(ownnerName))
                return car;
        }
        return null;
    }

    long totalClaimAmountByCustomer(String customerName) {
        long totalClaimed = 0;
        for (Claim claim : claims) {
            //if the given customer made the claim
            if (getContract(claim.getContractName()).getCustomerName().equals(customerName))
                //add the claim to the total
                totalClaimed += claim.getAmount();
        }
        return totalClaimed;
    }

    long totalReceivedAmountByCustomer(String customerName) {
        long totalReceived = 0;
        for (Claim claim : claims) {
            //use getContract() to get Contract object using the contract name
            Contract contract = getContract(claim.getContractName());
            //if the contract is the given customers
            if (contract.getCustomerName().equals(customerName)) {
                //and the claim was successful
                if (claim.wasSuccessful()) {
                    long deductible = getPlan(contract.getPlanName()).getDeductible();
                    //returns claim - deductible unless it is less than 0
                    totalReceived += Math.max(0, claim.getAmount() - deductible);
                }
            }
        }
        return totalReceived;
    }

    //function for counting number of customers in contract under a plan
    long numCustomersUnderPlan(String planName) {
        long numCustomers = 0;
        //go through array list of contracts
        for (Contract contract : contracts) {
            //if the given plan equals the plan that contract is under
            if (planName.equals(contract.getPlanName())) {
                numCustomers ++;
            }
        }
        return numCustomers;
    }

    //function for calculating total amount payed for claims under a plan
    long totalAmountPayedToCustomers(String planName) {
        long totalPayed = 0;
        //go through list of claims
        for (Claim claim : claims) {
            Contract contract = getContract(claim.getContractName());
            //if this contract is under the given plan
            if ((planName.equals(contract.getPlanName())) && (claim.wasSuccessful())) {
                long deductible = getPlan(contract.getPlanName()).getDeductible();
                //returns claim - deductible unless it is less than 0
                totalPayed += Math.max(0, claim.getAmount() - deductible);
            }
        }
        return totalPayed;
    }
}
