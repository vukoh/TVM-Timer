package seedu.address.logic.parser.finish;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.address.logic.commands.finish.FinishCommand;
import seedu.address.logic.commands.register.RegisterCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.BibNumber;
import seedu.address.model.person.Category;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.team.TeamNumber;
import seedu.address.model.time.EndTime;

/**
 * Parses input arguments and creates a new FinishCommand object
 */
public class FinishCommandParser implements Parser<FinishCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FinishCommand parse(String args) throws ParseException {

        EndTime endTime = new EndTime();

        return new FinishCommand(endTime);
    }
}
