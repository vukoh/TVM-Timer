package seedu.address.storage;

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
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.exceptions.StringToCategoryConversionException;
import seedu.address.model.team.TeamNumber;

/**
 * Jackson-friendly version of {@link PersonRegister}.
 */
class JsonAdaptedPersonRegister {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PersonRegister's %s field is missing!";

    private final String name;
    private final String bibNumber;
    private final String teamNumber;
    private final String category;

    /**
     * Constructs a {@code JsonAdaptedPersonRegister} with the given personRegister details.
     */
    @JsonCreator
    public JsonAdaptedPersonRegister(@JsonProperty("name") String name, @JsonProperty("bibNumber") String bibNumber,
                             @JsonProperty("teamNumber") String teamNumber, @JsonProperty("category") String category) {
        this.name = name;
        this.bibNumber = bibNumber;
        this.teamNumber = teamNumber;
        this.category = category;
    }

    /**
     * Converts a given {@code PersonRegister} into this class for Jackson use.
     */
    public JsonAdaptedPersonRegister(PersonRegister source) {
        name = source.getName().fullName;
        bibNumber = source.getBibNumber().bibNumber;
        teamNumber = source.getTeamNumber().teamNumber;
        category = source.getCategory().name();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code PersonRegister} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personRegister.
     */
    public PersonRegister toModelType() throws IllegalValueException {
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

        return new PersonRegister(modelName, modelBibNumber, modelTeamNumber, modelCategory);
    }

}
