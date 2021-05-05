
class RangeCriterion {
    private long maxValue = Long.MAX_VALUE;
    private long minValue = Long.MIN_VALUE;

    void addCriterion(Tag tag) {
        //if the symbol was >, that means minimum value has to be the given number
        //e.g x > 100 means x can not be lower than 100
        if (tag.getRelation() == Tag.Relation.LARGER) {
            minValue = Math.max(minValue, Long.parseLong(tag.getValue()));
        }
        //if symbol was <, maximum value has to be the given number associated with the tag
        if (tag.getRelation() == Tag.Relation.SMALLER) {
            maxValue = Math.min(maxValue, Long.parseLong(tag.getValue()));
        }
    }

    boolean isInRange(long value) {
        //if the mileage is between min and max
        if (value < maxValue && value > minValue)
            //it is in range
            return true;
        //otherwise it is not in range
        return false;
    }
}
