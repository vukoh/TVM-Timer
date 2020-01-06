package seedu.address.storage;

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
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonStart;
import seedu.address.model.person.exceptions.StringToCategoryConversionException;
import seedu.address.model.team.TeamNumber;
import seedu.address.model.time.StartTime;

/**
 * Jackson-friendly version of {@link PersonStart}.
 */
class JsonAdaptedPersonStart {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "PersonStart's %s field is missing!";

    private final String bibNumber;
    private final String startTime;
    private final boolean verified;

    /**
     * Constructs a {@code JsonAdaptedPersonStart} with the given personStart details.
     */
    @JsonCreator
    public JsonAdaptedPersonStart(@JsonProperty("bibNumber") String bibNumber,
                                     @JsonProperty("startTime") String startTime,
                                  @JsonProperty("verified") boolean verified) {
        this.bibNumber = bibNumber;
        this.startTime = startTime;
        this.verified = verified;
    }

    /**
     * Converts a given {@code PersonStart} into this class for Jackson use.
     */
    public JsonAdaptedPersonStart(PersonStart source) {
        bibNumber = source.getBibNumber().bibNumber;
        startTime = source.getStartTime().getStartTime().toString();
        verified = source.isVerified();
    }

    /**
     * Converts this Jackson-friendly adapted personStart object into the model's {@code PersonStart} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted personStart.
     */
    public PersonStart toModelType() throws IllegalValueException {

        if (bibNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, BibNumber.class.getSimpleName()));
        }
        /* Add when Bib Number constraints come in
        if (!bibNumber.isValidBibNumber(bibNumber)) {
            throw new IllegalValueException(BibNumber.MESSAGE_CONSTRAINTS);
        }
        */
        final BibNumber modelBibNumber = new BibNumber(bibNumber);
        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, StartTime.class.getSimpleName()));
        }
        final StartTime modelStartTime = new StartTime(Instant.parse(startTime));
        final boolean modelVerified = verified;

        return new PersonStart(modelBibNumber, modelStartTime, modelVerified);
    }

}
