package seedu.address.logic.commands.commandresults;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.logic.commands.CommandResult;

/**
 * Represents the result of a Register command execution.
 */
public class RegisterCommandResult extends CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a {@code RegisterCommandResult} with the specified fields.
     */
    public RegisterCommandResult(String feedbackToUser) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
    }

    @Override
    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    @Override
    public boolean isRegisterCommandResult() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RegisterCommandResult)) {
            return false;
        }

        RegisterCommandResult otherRegisterCommandResult = (RegisterCommandResult) other;
        return feedbackToUser.equals(otherRegisterCommandResult.feedbackToUser);
    }
    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser);
    }

}
