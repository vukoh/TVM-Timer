package seedu.address.model.person;

import seedu.address.model.person.exceptions.StringToCategoryConversionException;

/**
 * Enum class for the different categories a person may be participating in.
 */
public enum Category {
    NUS_MEN,
    NUS_WOMEN,
    OPEN_MEN,
    OPEN_WOMEN;

    private int numberCode;

    public static final String MESSAGE_CONSTRAINTS = "Category should only be the 4 category"
            + "codes: \n"
            + " 1: NUS Men \n"
            + " 2: NUS Women \n 3: Open Men \n 4: Open Women";

    static {
        NUS_MEN.numberCode = 1;
        NUS_WOMEN.numberCode = 2;
        OPEN_MEN.numberCode = 3;
        OPEN_WOMEN.numberCode = 4;
    }

    public static Category getCategoryFromString(String stringIntegerCategory) throws StringToCategoryConversionException {
        switch (stringIntegerCategory) {
        case "1" :
            return NUS_MEN;
        case "2":
            return NUS_WOMEN;
        case "3":
            return OPEN_MEN;
        case "4":
            return OPEN_WOMEN;
        default:
            throw new StringToCategoryConversionException();
        }
    }


}

