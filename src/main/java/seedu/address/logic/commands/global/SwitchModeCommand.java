package seedu.address.logic.commands.global;

import java.util.Optional;

import seedu.address.logic.FunctionMode;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.model.Model;

/**
 * Toggles between the different functions of the application.
 */
public class SwitchModeCommand extends Command {
    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches between the respective functions and"
            + " commands available.\n"
            + "Parameters: MODE ABBREVIATION\n"
            + "Options:\n"
            + COMMAND_WORD + " register\n"
            + COMMAND_WORD + " start\n"
            + COMMAND_WORD + " finish\n"
            + COMMAND_WORD + " calculate";

    private static final String SWITCH_TO_REGISTER_FUNCTION_FEEDBACK = "You are currently in register mode!";
    private static final String SWITCH_TO_START_FUNCTION_FEEDBACK = "You are currently in start mode!";
    private static final String SWITCH_TO_FINISH_FUNCTION_FEEDBACK = "You are currently in finish mode!";
    private static final String SWITCH_TO_COMPLETE_FUNCTION_FEEDBACK = "You are currently in complete mode!";
    private static final String SWITCH_TO_CALCULATE_FUNCTION_FEEDBACK = "You are currently in calculate mode!";

    private final FunctionMode targetMode;

    public SwitchModeCommand(FunctionMode targetMode) {
        this.targetMode = targetMode;
    }

    @Override
    public CommandResult execute(Model model) {
        String feedBackString;
        switch (targetMode) {
        case REGISTER:
            feedBackString = SWITCH_TO_REGISTER_FUNCTION_FEEDBACK;
            LogicManager.setMode(FunctionMode.REGISTER);
            break;
        case START:
            feedBackString = SWITCH_TO_START_FUNCTION_FEEDBACK;
            LogicManager.setMode(FunctionMode.START);
            break;
        case FINISH:
            feedBackString = SWITCH_TO_FINISH_FUNCTION_FEEDBACK;
            LogicManager.setMode(FunctionMode.FINISH);
            break;
        case COMPLETE:
            feedBackString = SWITCH_TO_COMPLETE_FUNCTION_FEEDBACK;
            LogicManager.setMode(FunctionMode.COMPLETE);
            break;
        case CALCULATE:
            feedBackString = SWITCH_TO_CALCULATE_FUNCTION_FEEDBACK;
            LogicManager.setMode(FunctionMode.CALCULATE);
            break;
        default:
            feedBackString = null; // To be re-implemented
            LogicManager.setMode(FunctionMode.UNDEFINED);
        }
        return new GlobalCommandResult(feedBackString, false, false, true,
                Optional.of(targetMode));
    }

    public FunctionMode getTargetMode() {
        // Used in MainWindow
        return targetMode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchModeCommand // instanceof handles nulls
                && targetMode.equals(((SwitchModeCommand) other).targetMode)); // state check
    }
}
