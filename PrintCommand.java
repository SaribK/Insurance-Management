import java.text.ParseException;

class PrintCommand extends Command {
    private String entityType;
    private String queryType;
    private String queryValue;

    PrintCommand(String[] tokens) {
        super();
        //second string in list is which object's information they want to print
        // e.g CUSTOMER
        entityType = tokens[1];
        //third string is what information they want
        queryType = tokens[2];
        //fourth is value used to identify the object
        // e.g for customer, it would be customer name
        queryValue = tokens[3];
    }

    @Override
    void run(Database database) {
        //if they ask for CUSTOMER or PLAN, run function associated with either object
        if (entityType.equals("CUSTOMER"))
            runPrintCustomer(database);
        else if (entityType.equals("PLAN"))
            runPrintPlan(database);
        else {
            throw new BadCommandException("Bad print command.");
        }
    }

    private void runPrintCustomer(Database database) {
        //if they want TOTAL_CLAIMED and customer exists
        if (queryType.equals("TOTAL_CLAIMED") && database.getCustomer(queryValue) != null) {
            //use Database to get the customer's name and the customer's total claimed amount
            System.out.println("Total amount claimed by " + database.getCustomer(queryValue).getName() +
                    " is " + database.totalClaimAmountByCustomer(queryValue));
        }
        //if they want TOTAL_RECEIVED and customer exists
        else if (queryType.equals("TOTAL_RECEIVED") && database.getCustomer(queryValue) != null) {
            //use Database to get the customer's name and the customer's total received amount
            System.out.println("Total amount received by " + database.getCustomer(queryValue).getName() +
                            " is " + database.totalReceivedAmountByCustomer(queryValue));
        }
        //if they want total claimed and customer does not exist
        else if (queryType.equals("TOTAL_CLAIMED") && database.getCustomer(queryValue) == null) {
            System.out.println("Total amount claimed by " + queryValue +
                    " is " + 0);

        }
        //if they want total received and customer does not exist
        else if (queryType.equals("TOTAL_RECEIVED") && database.getCustomer(queryValue) == null) {
            System.out.println("Total amount received by " + queryValue +
                    " is " + 0);
        }
        else { //if something else was inputted then throw error
            throw new BadCommandException("Invalid PRINT CUSTOMER command.");
        }
    }

    //TODO
    private void runPrintPlan(Database database) {
        //if they want num customers and the plan exists
        if (queryType.equals("NUM_CUSTOMERS") && database.getPlan(queryValue) != null) {
            System.out.println("Number of customers under " + queryValue +
                    " is " + database.numCustomersUnderPlan(queryValue));
        }
        //if they want total payed and plan exists
        else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS") && database.getPlan(queryValue) != null) {
            System.out.println("Total amount payed under " + queryValue +
                    " is " + database.totalAmountPayedToCustomers(queryValue));
        }
        //if they want num customers and plan does not exist
        else if (queryType.equals("NUM_CUSTOMERS") && database.getPlan(queryValue) == null) {
            System.out.println("Number of customers under " + queryValue +
                    " is " + 0);
        }
        //if they want total payed and plan does not exist
        else if (queryType.equals("TOTAL_PAYED_TO_CUSTOMERS") && database.getPlan(queryValue) == null) {
            System.out.println("Total amount payed under " + queryValue +
                    " is " + 0);
        }
        else {
            throw new BadCommandException("Invalid PRINT PLAN command.");
        }
    }
}
