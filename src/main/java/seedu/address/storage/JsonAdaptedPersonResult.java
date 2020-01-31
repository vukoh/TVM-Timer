package seedu.address.storage;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.BibNumber;
import seedu.address.model.person.Category;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.exceptions.StringToCategoryConversionException;
import seedu.address.model.team.TeamNumber;
import seedu.address.model.time.EndTime;
import seedu.address.model.time.StartTime;

/**
 * Jackson-friendly version of {@link PersonResult}.
 */
class JsonAdaptedPersonResult {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PersonResult's %s field is missing!";

    private final String name;
    private final String bibNumber;
    private final String teamNumber;
    private final String category;
    private final String startTime;
    private final String endTime;
    private final String timeTaken;

    /**
     * Constructs a {@code JsonAdaptedPersonResult} with the given personResult details.
     */
    @JsonCreator
    public JsonAdaptedPersonResult(@JsonProperty("name") String name, @JsonProperty("bibNumber") String bibNumber,
                                     @JsonProperty("teamNumber") String teamNumber,
                                   @JsonProperty("category") String category,
                                   @JsonProperty("startTime") String startTime, @JsonProperty("endTime") String endTime,
                                   @JsonProperty("timeTaken") String timeTaken) {
        this.name = name;
        this.bibNumber = bibNumber;
        this.teamNumber = teamNumber;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeTaken = timeTaken;
    }

    /**
     * Converts a given {@code PersonResult} into this class for Jackson use.
     */
    public JsonAdaptedPersonResult(PersonResult source) {
        name = source.getName().fullName;
        bibNumber = source.getBibNumber().bibNumber;
        teamNumber = source.getTeamNumber().teamNumber;
        category = source.getCategory().name();
        startTime = source.getStartTime().getStartTime().toString();
        endTime = source.getEndTime().getEndTime().toString();
        timeTaken = source.getTimeTaken() + "";
    }

    /**
     * Converts this Jackson-friendly adapted personResult object into the model's {@code PersonResult} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personResult.
     */
    public PersonResult toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (bibNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, BibNumber.class.getSimpleName()));
        }
        /* Add when Bib Number constraints come in
        if (!bibNumber.isValidBibNumber(bibNumber)) {
            throw new IllegalValueException(BibNumber.MESSAGE_CONSTRAINTS);
        }
        */
        final BibNumber modelBibNumber = new BibNumber(bibNumber);

        if (teamNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TeamNumber.class.getSimpleName()));
        }
        /* Add when Team Number constraints come in
        if (!teamNumber.isValidTeamNumber(teamNumber)) {
            throw new IllegalValueException(TeamNumber.MESSAGE_CONSTRAINTS);
        }
        */
        final TeamNumber modelTeamNumber = new TeamNumber(teamNumber);

        Category modelCategory = null;
        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Category.class.getSimpleName()));
        }
        try {
            modelCategory = Category.getCategoryFromString(category);
        } catch (StringToCategoryConversionException e) {
            throw new IllegalValueException(e.getMessage());
        }
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName()));
        }
        final StartTime modelStartTime = new StartTime(Instant.parse(startTime));
        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName()));
        }
        final EndTime modelEndTime = new EndTime(Instant.parse(endTime));
        if (timeTaken == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Long.class.getSimpleName()));
        }
        final long modelTimeTaken = Duration.parse(timeTaken).getSeconds();


        return new PersonResult(modelName, modelBibNumber, modelTeamNumber, modelCategory, modelStartTime,
                modelEndTime, modelTimeTaken);
    }

}
