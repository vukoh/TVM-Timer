package seedu.address.logic.commands;

/**
 * Represents the result of a command execution.
 */
public abstract class CommandResult {

    public abstract String getFeedbackToUser();

    public boolean isGlobalCommandResult() {
        return false;
    }

    public boolean isRegisterCommandResult() {
        return false;
    }

    public boolean isStartCommandResult() {
        return false;
    }

    public boolean isFinishCommandResult() {
        return false;
    }

    public boolean isCalculateCommandResult() {
        return false;
    }

    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

}
