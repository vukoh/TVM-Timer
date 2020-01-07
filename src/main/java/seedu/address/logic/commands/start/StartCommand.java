package seedu.address.logic.commands.start;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;

/**
 * Edits the details of an existing person in the address book.
 */
public class StartCommand extends Command {

    public static final String COMMAND_WORD = "start";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Records and starts the timer for the racers"
            + "with the bib numbers specified. \n"
            + "Parameters: "
            + "[" + PREFIX_BIB_NUMBER + "BIB NUMBER....] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_BIB_NUMBER + "14"
            + PREFIX_BIB_NUMBER + "120"
            + PREFIX_BIB_NUMBER + "16";

    public static final String MESSAGE_START_SUCCESS = "Timer for the racers specified has successfully been " +
            "started!";
    public static final String MESSAGE_NO_BIB_NUMBER = "At least one bib number must be specified.";
    public static final String MESSAGE_RACER_NOT_FOUND = "One of the bib number specified has yet to be registered!";

    private final ArrayList<BibNumber> bibNumbers;

    /**
     * @param bibNumbers of the racers to start
     */
    public StartCommand(ArrayList<BibNumber> bibNumbers) {
        requireNonNull(bibNumbers);

        this.bibNumbers = bibNumbers;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<PersonRegister> lastShownList = model.getFilteredPersonRegisterList();

        if (bibNumbers.isEmpty()) {
            throw new CommandException(MESSAGE_NO_BIB_NUMBER);
        }

        Instant instant = Instant.now();

        for (BibNumber bibNumber : bibNumbers) {
            PersonStart personStart = new PersonStart(bibNumber, instant);
            
            if (!model.hasPersonStart(personStart)) {
                model.addPersonStart(personStart);
            }
        }

        return new GlobalCommandResult(MESSAGE_START_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartCommand)) {
            return false;
        }

        // state check
        StartCommand e = (StartCommand) other;
        return bibNumbers.equals(e.bibNumbers);
    }


}
