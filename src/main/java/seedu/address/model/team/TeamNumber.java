package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a TeamNumber in this application (for Team objects).
 * Guarantees: immutable; is valid as declared in {@link #isValidTeamNumber(String)}
 */
public class TeamNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "TeamNumbers should only contain alphanumeric characters and spaces, and it should not be blank";


    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String teamNumber;

    /**
     * Constructs a {@code TeamNumber}.
     *
     * @param rawTeamNumber A valid team number.
     */
    public TeamNumber(String rawTeamNumber) {
        requireNonNull(rawTeamNumber);
        checkArgument(isValidTeamNumber(rawTeamNumber), MESSAGE_CONSTRAINTS);
        teamNumber = rawTeamNumber;
    }

    /**
     * Returns true if a given string is a valid team number.
     */
    public static boolean isValidTeamNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return teamNumber;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamNumber // instanceof handles nulls
                && teamNumber.equals(((TeamNumber) other).teamNumber)); // state check
    }

    @Override
    public int hashCode() {
        return teamNumber.hashCode();
    }

}
