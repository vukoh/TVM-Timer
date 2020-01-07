package seedu.address.logic.commands.finish;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.time.EndTime;

/**
 * Adds a record of the end time of a racer.
 */
public class FinishCommand extends Command {

    public static final String COMMAND_WORD = "finish";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a record of the finishing time of a racer.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "New finish time added: %1$s";

    private final EndTime toAdd;

    /**
     * Creates an FinishCommand to add the specified {@code EndTime}
     */
    public FinishCommand(EndTime endTime) {
        requireNonNull(endTime);
        this.toAdd = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        //TODO Add EndTime toAdd into the UniqueEndTimeList

        return new GlobalCommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FinishCommand // instanceof handles nulls
                && toAdd.equals(((FinishCommand) other).toAdd));
    }
}
