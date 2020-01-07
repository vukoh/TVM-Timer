package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.time.EndTime;

/**
 * An Immutable EndTimes class that is serializable to JSON format.
 */
@JsonRootName(value = "endTimes")
class JsonSerializableEndTime {

    public static final String MESSAGE_DUPLICATE_END_TIME = "EndTime list contains duplicate " +
            "endTime(s).";

    private final List<JsonAdaptedEndTime> endTimes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEndTime} with the given endTimes.
     */
    @JsonCreator
    public JsonSerializableEndTime(@JsonProperty("endTimes") List<JsonAdaptedEndTime> endTimes) {
        this.endTimes.addAll(endTimes);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableEndTime(ReadOnlyAddressBook source) {
        endTimes.addAll(source.getEndTimeList().stream().map(JsonAdaptedEndTime::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedEndTime jsonAdaptedEndTime : endTimes) {
            EndTime endTime = jsonAdaptedEndTime.toModelType();
            if (addressBook.hasEndTime(endTime)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_END_TIME);
            }
            addressBook.addEndTime(endTime);
        }
        return addressBook;
    }

}
