package buzzquizworld.model.category;

/**
 * Enum that contains types of question categories.
 * @author Dimitris
 */
public enum Category {
    MATHS,HISTORY,GEOGRAPHY,SPORTS,SCIENCE,MUSIC;

    /**
     * @return the type of category as String
     */
    @Override
    public String toString() {
        switch (this){
            case MATHS:
                return "Maths";
            case HISTORY:
                return "History";
            case GEOGRAPHY:
                return "Geography";
            case SPORTS:
                return "Sports";
            case SCIENCE:
                return "Science";
            case MUSIC:
                return "Music";
            default:
                return "";
        }
    }
}
