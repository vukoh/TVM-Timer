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
import seedu.address.model.time.EndTime;

/**
 * Jackson-friendly version of {@link EndTime}.
 */
class JsonAdaptedEndTime {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "EndTime's %s field is missing!";

    private final String endTime;

    /**
     * Constructs a {@code JsonAdaptedEndTime} with the given endTime details.
     */
    @JsonCreator
    public JsonAdaptedEndTime(@JsonProperty("endTime") String endTime) {

        this.endTime = endTime;
    }

    /**
     * Converts a given {@code EndTime} into this class for Jackson use.
     */
    public JsonAdaptedEndTime(EndTime source) {
        endTime = source.getEndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted endTime object into the model's {@code endTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted endTime.
     */
    public EndTime toModelType() throws IllegalValueException {

        if (endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, EndTime.class.getSimpleName()));
        }
        final Instant modelInstant =  Instant.parse(endTime);

        return new EndTime(modelInstant);
    }

}
