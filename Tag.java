
class Tag {
    //defines 3 fixed enum constants
    public enum Relation {
        SMALLER, LARGER, EQUAL
    }
    private Relation relation;
    private String name;
    private String value;

    Tag(String[] tokens) {
        //first string is the name of the variable e.g NAME = ROBERT
        name = tokens[0];
        //checks the first char in the second string of list
        //it can be one of =, >, or <
        switch (tokens[1].charAt(0)) {
            case '<':
                //< is less than symbol, so relation is SMALLER
                relation = Relation.SMALLER;
                break;
            case '>':
                //> is greater than
                relation = Relation.LARGER;
                break;
            case '=':
                //= is equals
                relation = Relation.EQUAL;
                break;
            default:
                //if none of the cases are met, then input was wrong
                throw new BadCommandException("Invalid tag: ill-defined bad relation.");
        }
        //third string is value of the variable
        value = tokens[2];
    }
    //getter functions to get this class's variables
    public Relation getRelation() {
        return relation;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}