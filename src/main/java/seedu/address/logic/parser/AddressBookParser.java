package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.SPECIFY_MODE;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.finish.CompleteCommand;
import seedu.address.logic.commands.finish.FinishCommand;
import seedu.address.logic.commands.global.SwitchModeCommand;
import seedu.address.logic.commands.register.RegisterCommand;
import seedu.address.logic.commands.start.StartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.finish.CompleteCommandParser;
import seedu.address.logic.parser.finish.FinishCommandParser;
import seedu.address.logic.parser.register.RegisterCommandParser;
import seedu.address.logic.parser.start.StartCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case SwitchModeCommand.COMMAND_WORD:
            return new SwitchModeCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            switch (LogicManager.getMode()) {
            case REGISTER:
                return parseRegisterCommands(commandWord, arguments);

            case START:
                return parseStartCommands(commandWord, arguments);

            case FINISH:
                return parseFinishCommands(commandWord, arguments);

            case CALCULATE:
                return parseCalculateCommands(commandWord, arguments);

            default:
                throw new ParseException(SPECIFY_MODE);
            }
        }
    }

    /**
     * Parses user input into command for execution for 'Register' mode commands.
     * @param commandWord the command to execute
     * @param arguments the parameters supplied to command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseRegisterCommands(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case RegisterCommand.COMMAND_WORD:
            return new RegisterCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution for 'Start' mode commands.
     * @param commandWord the command to execute
     * @param arguments the parameters supplied to command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseStartCommands(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

        case StartCommand.COMMAND_WORD:
            return new StartCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution for 'Finish' mode commands.
     * @param commandWord the command to execute
     * @param arguments the parameters supplied to command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseFinishCommands(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {

            case FinishCommand.COMMAND_WORD:
                return new FinishCommandParser().parse(arguments);

            case CompleteCommand.COMMAND_WORD:
                return new CompleteCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses user input into command for execution for 'Calculate' mode commands.
     * @param commandWord the command to execute
     * @param arguments the parameters supplied to command
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseCalculateCommands(String commandWord, String arguments) throws ParseException {
        switch (commandWord) {
            // To add in commands
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
