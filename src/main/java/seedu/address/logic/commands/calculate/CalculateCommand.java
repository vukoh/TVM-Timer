package seedu.address.logic.commands.calculate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.team.TeamNumber;
import seedu.address.model.time.EndTime;
import seedu.address.model.time.StartTime;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.Objects.requireNonNull;

/**
 * Command to calculate the final timing and rankings of all participants and
 * exports them to an excel file.
 */
public class CalculateCommand extends Command {

    public static final String COMMAND_WORD = "calculate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates the final timing and ranking of all "
            + "participants and exports them to an excel file\n"
            + "Parameters: "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Calculation and ranking successfully executed adn exported.";


    public CalculateCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        PriorityQueue<PersonResult> resultQueue = new PriorityQueue<>((PersonResult person, PersonResult other) -> {
            int categoryDifference = Category.getCategoryCode(person.getCategory())
                    - Category.getCategoryCode(other.getCategory());
            if (categoryDifference == 0) {
                return person.getTimeTaken().compareTo(other.getTimeTaken());
            } else {
                return categoryDifference;
            }
        });

        ObservableList<PersonRegister> registers = model.getPersonRegisters();
        ObservableList<PersonStart> starts = model.getPersonStarts();
        ObservableList<EndTime> endTimes = model.getEndTimes();
        ObservableList<PersonEnd> ends = model.getPersonEnds();

        for (PersonRegister personRegister: registers) {
            Name name = personRegister.getName();
            BibNumber bibNumber = personRegister.getBibNumber();
            TeamNumber teamNumber = personRegister.getTeamNumber();
            Category category = personRegister.getCategory();
            StartTime startTime = null;
            EndTime endTime = null;
            Duration timeTaken;

            for (PersonStart personStart : starts) {
                if (personStart.getBibNumber().equals(bibNumber)) {
                    startTime = personStart.getStartTime();
                }
            }
            for (PersonEnd personEnd : ends) {
                if (personEnd.getBibNumber().equals(bibNumber)) {
                    endTime = endTimes.get(personEnd.getIndex().index);
                }
            }

            if (startTime == null || endTime == null) {
                startTime = new StartTime(Instant.MIN);
                endTime = new EndTime(Instant.MAX);
            }
            timeTaken = Duration.between(startTime.getStartTime(), endTime.getEndTime());
            PersonResult personResult = new PersonResult(name, bibNumber, teamNumber, category,
                    startTime, endTime, timeTaken);
            resultQueue.add(personResult);
        }

        List<PersonResult> personResults = new ArrayList<>(resultQueue);
        model.setPersonResults(personResults);
        //TODO export to excel file
        return new GlobalCommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalculateCommand);// instanceof handles nulls
    }
}

