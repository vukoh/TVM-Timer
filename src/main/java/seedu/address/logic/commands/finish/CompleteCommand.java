package seedu.address.logic.commands.finish;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonEnd;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Toggles between the different functions of the application.
 */
public class CompleteCommand extends Command {

    public static final String COMMAND_WORD = "complete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a racer's bib number to their position.\n"
            + "Parameters: "
            + PREFIX_BIB_NUMBER + "BIB NUMBER "
            + PREFIX_INDEX + "POSITION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BIB_NUMBER + "666 "
            + PREFIX_INDEX + "15";

    public static final String MESSAGE_SUCCESS = "Racer %1$s has completed the Race";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the completed list";

    private final PersonEnd toComplete;

    public CompleteCommand(PersonEnd personEnd) {
        this.toComplete = personEnd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPersonEnd(toComplete)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPersonEnd(toComplete);

        return new GlobalCommandResult(String.format(MESSAGE_SUCCESS, toComplete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompleteCommand // instanceof handles nulls
                && toComplete.equals(((CompleteCommand) other).toComplete));
    }
}
