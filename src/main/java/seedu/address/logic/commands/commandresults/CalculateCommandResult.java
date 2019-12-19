package seedu.address.logic.commands.commandresults;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.CommandResult;

/**
 * Represents the result of a command execution.
 */
public class CalculateCommandResult extends CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a {@code CalculateCommandResult} with the specified fields.
     */
    public CalculateCommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }

    @Override
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    @Override
    public boolean isCalculateCommandResult() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalculateCommandResult)) {
            return false;
        }

        CalculateCommandResult otherCalculateCommandResult = (CalculateCommandResult) other;
        return feedbackToUser.equals(otherCalculateCommandResult.feedbackToUser);
    }
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser);
    }

}
