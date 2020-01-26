package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.FunctionMode;
import seedu.address.logic.commands.global.SwitchModeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SwitchModeCommand object
 */
public class SwitchModeCommandParser implements Parser<SwitchModeCommand> {

    private static final String REGISTER = "REGISTER";
    private static final String START = "START";
    private static final String FINISH = "FINISH";
    private static final String COMPLETE = "COMPLETE";
    private static final String CALCULATE = "CALCULATE";


    /**
     * Parses the given {@code String} of arguments in the context of the SwitchModeCommand
     * and returns a SwitchModeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SwitchModeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchModeCommand.MESSAGE_USAGE));
        }
        switch (trimmedArgs.toUpperCase()) {
        case REGISTER:
            return new SwitchModeCommand(FunctionMode.REGISTER);

        case START:
            return new SwitchModeCommand(FunctionMode.START);

        case FINISH:
            return new SwitchModeCommand(FunctionMode.FINISH);

        case COMPLETE:
            return new SwitchModeCommand(FunctionMode.COMPLETE);

        case CALCULATE:
            return new SwitchModeCommand(FunctionMode.CALCULATE);

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchModeCommand.MESSAGE_USAGE));
        }
    }
}
