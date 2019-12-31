package seedu.address.model.person.exceptions;

/**
 * Signals that the conversion from a Category string to a Category was not successful because none
 * of the cases were matched.
 */
public class StringToCategoryConversionException extends RuntimeException {

    public static final String ERROR_MESSAGE = "Exception while converting Category string in JSON storage "
            + "to Category object!";

    public StringToCategoryConversionException() {
        super(ERROR_MESSAGE);
    }
}
