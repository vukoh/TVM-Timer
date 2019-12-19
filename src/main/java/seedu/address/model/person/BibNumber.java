package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a BibNumber in this application (for PersonRegister, PersonStart and PersonResult objects).
 * Guarantees: immutable; is valid as declared in {@link #isValidBibNumber(String)}
 */
public class BibNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "BibNumbers should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String bibNumber;

    /**
     * Constructs a {@code BibNumber}.
     *
     * @param rawBibNumber A valid bib number.
     */
    public BibNumber(String rawBibNumber) {
        requireNonNull(rawBibNumber);
        checkArgument(isValidBibNumber(rawBibNumber), MESSAGE_CONSTRAINTS);
        bibNumber = rawBibNumber;
    }

    /**
     * Returns true if a given string is a valid bib number.
     */
    public static boolean isValidBibNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return bibNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BibNumber // instanceof handles nulls
                && bibNumber.equals(((BibNumber) other).bibNumber)); // state check
    }

    @Override
    public int hashCode() {
        return bibNumber.hashCode();
    }

}
