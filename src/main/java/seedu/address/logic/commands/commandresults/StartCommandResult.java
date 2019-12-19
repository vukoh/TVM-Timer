package seedu.address.logic.commands.commandresults;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.CommandResult;

/**
 * Represents the result of a Start command execution.
 */
public class StartCommandResult extends CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a {@code StartCommandResult} with the specified fields.
     */
    public StartCommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }

    @Override
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    @Override
    public boolean isStartCommandResult() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartCommandResult)) {
            return false;
        }

        StartCommandResult otherStartCommandResult = (StartCommandResult) other;
        return feedbackToUser.equals(otherStartCommandResult.feedbackToUser);
    }
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser);
    }

}
