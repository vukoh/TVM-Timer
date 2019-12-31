package seedu.address.logic.commands.register;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonRegister;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Toggles between the different functions of the application.
 */
public class RegisterCommand extends Command {

    public static final String COMMAND_WORD = "register";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a new racer into the race list.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_BIB_NUMBER + "BIB NUMBER "
            + PREFIX_TEAM_NUMBER + "TEAM NUMBER "
            + PREFIX_CATEGORY + "CATEGORY\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Khairul Gat "
            + PREFIX_BIB_NUMBER + "666 "
            + PREFIX_TEAM_NUMBER + "15 "
            + PREFIX_CATEGORY + "NUS_WOMEN ";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the race list";

    private final PersonRegister toRegister;

    public RegisterCommand(PersonRegister personRegister) {
        this.toRegister = personRegister;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPersonRegister(toRegister)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPersonRegister(toRegister);
        return new GlobalCommandResult(String.format(MESSAGE_SUCCESS, toRegister));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RegisterCommand // instanceof handles nulls
                && toRegister.equals(((RegisterCommand) other).toRegister));
    }
}

