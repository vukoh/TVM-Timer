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

    public static Category getCategoryFromString(String stringCategory) throws StringToCategoryConversionException {
        switch (stringCategory) {
        case "NUS_MEN":
            return NUS_MEN;
        case "NUS_WOMEN":
            return NUS_WOMEN;
        case "OPEN_MEN":
            return OPEN_MEN;
        case "OPEN_WOMEN":
            return OPEN_WOMEN;
        default:
            throw new StringToCategoryConversionException();
        }
    }
}