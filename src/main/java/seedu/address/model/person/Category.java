package seedu.address.model.person;

/**
 * Enum class for the different categories a person may be participating in.
 */
public enum Category {
    NUS_MEN,
    NUS_WOMEN,
    OPEN_MEN,
    OPEN_WOMEN;

    public static final String MESSAGE_CONSTRAINTS = "Category should only be the 4 category"
            + "codes: \n"
            + " 1: NUS Men \n"
            + " 2: NUS Women \n 3: Open Men \n 4: Open Women";
}
