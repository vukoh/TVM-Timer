package seedu.address.logic.parser.calculate;

import seedu.address.logic.commands.calculate.CalculateCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class CalculateCommandParser implements Parser<CalculateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CalculateCommand parse(String args) throws ParseException {
        return new CalculateCommand();
    }

}
