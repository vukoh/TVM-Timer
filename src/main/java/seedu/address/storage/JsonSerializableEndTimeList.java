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
import seedu.address.model.person.PersonStart;

/**
 * An Immutable EndTimeList class that is serializable to JSON format.
 */
@JsonRootName(value = "endtimelist")
class JsonSerializableEndTimeList {

    public static final String MESSAGE_DUPLICATE_PERSON_START = "PersonStart list contains duplicate " +
            "personStart(s).";

    private final List<JsonAdaptedPersonStart> personStarts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonStart} with the given personStarts.
     */
    @JsonCreator
    public JsonSerializablePersonStart(@JsonProperty("personstarts") List<JsonAdaptedPersonStart> personStarts) {
        this.personStarts.addAll(personStarts);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePersonStart(ReadOnlyAddressBook source) {
        personStarts.addAll(source.getPersonStartList().stream().map(JsonAdaptedPersonStart::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPersonStart jsonAdaptedPersonStart : personStarts) {
            PersonStart personStart = jsonAdaptedPersonStart.toModelType();
            if (addressBook.hasPersonStart(personStart)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON_START);
            }
            addressBook.addPersonStart(personStart);
        }
        return addressBook;
    }

}
