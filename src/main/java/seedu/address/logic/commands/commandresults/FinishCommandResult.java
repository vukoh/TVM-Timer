package seedu.address.logic.commands.commandresults;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.CommandResult;

/**
 * Represents the result of a Finish command execution.
 */
public class FinishCommandResult extends CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a {@code FinishCommandResult} with the specified fields.
     */
    public FinishCommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }

    @Override
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    @Override
    public boolean isFinishCommandResult() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FinishCommandResult)) {
            return false;
        }

        FinishCommandResult otherFinishCommandResult = (FinishCommandResult) other;
        return feedbackToUser.equals(otherFinishCommandResult.feedbackToUser);
    }
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser);
    }

}
