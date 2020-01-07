package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class EndTimeListIndex {

    public static final String MESSAGE_CONSTRAINTS =
            "Index should only contain positive, non-zero integers.";

    public static final String VALIDATION_REGEX = "^[1-9]\\d*$";

    public final Integer index;

    /**
     * Constructs a {@code EndTimeListIndex}.
     *
     * @param index A valid index.
     */
    public EndTimeListIndex(String index) {
        requireNonNull(index);
        checkArgument(isValidIndex(index), MESSAGE_CONSTRAINTS);
        this.index = Integer.parseInt(index);
    }

    /**
     * Returns true if a given string is a valid index.
     */
    public static boolean isValidIndex(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return index.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTimeListIndex // instanceof handles nulls
                && index.equals(((EndTimeListIndex) other).index)); // state check
    }

    @Override
    public int hashCode() {
        return index.hashCode();
    }

}
