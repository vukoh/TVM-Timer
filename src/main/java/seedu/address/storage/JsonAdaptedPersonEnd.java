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
import seedu.address.model.person.EndTimeListIndex;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.person.exceptions.StringToCategoryConversionException;
import seedu.address.model.team.TeamNumber;

/**
 * Jackson-friendly version of {@link PersonEnd}.
 */
class JsonAdaptedPersonEnd {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PersonEnd's %s field is missing!";

    private final String bibNumber;
    private final String index;

    /**
     * Constructs a {@code JsonAdaptedPersonEnd} with the given personEnd details.
     */
    @JsonCreator
    public JsonAdaptedPersonEnd(@JsonProperty("bibNumber") String bibNumber, @JsonProperty("index") String index) {
        this.bibNumber = bibNumber;
        this.index = index;
    }

    /**
     * Converts a given {@code PersonEnd} into this class for Jackson use.
     */
    public JsonAdaptedPersonEnd(PersonEnd source) {
        bibNumber = source.getBibNumber().bibNumber;
        index = Integer.toString(source.getIndex().index);
    }

    /**
     * Converts this Jackson-friendly adapted personEnd object into the model's {@code PersonEnd} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personEnd.
     */
    public PersonEnd toModelType() throws IllegalValueException {

        if (bibNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, BibNumber.class.getSimpleName()));
        }
        /* Add when Bib Number constraints come in
        if (!bibNumber.isValidBibNumber(bibNumber)) {
            throw new IllegalValueException(BibNumber.MESSAGE_CONSTRAINTS);
        }
        */
        final BibNumber modelBibNumber = new BibNumber(bibNumber);

        if (index == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTimeListIndex.class.getSimpleName()));
        }

        // More defensive coding to check? TODO
        EndTimeListIndex modelIndex = new EndTimeListIndex(index);

        return new PersonEnd(modelBibNumber, modelIndex);
    }

}
