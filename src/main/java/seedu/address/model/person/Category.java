package seedu.address.model.person;

import seedu.address.model.person.exceptions.StringToCategoryConversionException;

/**
 * Enum class for the different categories a person may be participating in.
 */
public enum Category {
    NUS_MEN,
    NUS_WOMEN,
    OPEN_MEN,
    OPEN_WOMEN,
    TEAM;

    private int numberCode;

    public static final String MESSAGE_CONSTRAINTS = "Category should only be the 5 category"
            + "codes: \n"
            + " 1: NUS Men \n"
            + " 2: NUS Women \n 3: Open Men \n 4: Open Women \n 5: Team";

    static {
        NUS_MEN.numberCode = 1;
        NUS_WOMEN.numberCode = 2;
        OPEN_MEN.numberCode = 3;
        OPEN_WOMEN.numberCode = 4;
        TEAM.numberCode = 5;
    }

    public static Category getCategoryFromString(String stringCategory) throws StringToCategoryConversionException {
        switch (stringCategory) {
        case "NUS_MEN" :
            return NUS_MEN;
        case "NUS_WOMEN":
            return NUS_WOMEN;
        case "OPEN_MEN":
            return OPEN_MEN;
        case "OPEN_WOMEN":
            return OPEN_WOMEN;
        case "TEAM":
            return TEAM;
        default:
            throw new StringToCategoryConversionException();
        }
    }

    public static Category getCategoryFromIntString(String stringIntCategory) throws StringToCategoryConversionException {
        switch (stringIntCategory) {
            case "1" :
                return NUS_MEN;
            case "2":
                return NUS_WOMEN;
            case "3":
                return OPEN_MEN;
            case "4":
                return OPEN_WOMEN;
            case "5":
                return TEAM;
            default:
                throw new StringToCategoryConversionException();
        }
    }

    public static int getCategoryCode(Category category) {
        switch (category) {
            case NUS_MEN:
                return 1;
            case NUS_WOMEN:
                return 2;
            case OPEN_MEN:
                return 3;
            case OPEN_WOMEN:
                return 4;
            case TEAM:
                return 5;
            default:
                return 0;
        }
    }

}

