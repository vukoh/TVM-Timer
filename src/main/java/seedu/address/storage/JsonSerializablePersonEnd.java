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
import seedu.address.model.person.PersonEnd;

/**
 * An Immutable PersonEnds class that is serializable to JSON format.
 */
@JsonRootName(value = "personEnds")
class JsonSerializablePersonEnd {

    public static final String MESSAGE_DUPLICATE_PERSON_END = "PersonEnd list contains duplicate " +
            "personEnd(s).";

    private final List<JsonAdaptedPersonEnd> personEnds = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonEnd} with the given personEnds.
     */
    @JsonCreator
    public JsonSerializablePersonEnd(@JsonProperty("personEnds") List<JsonAdaptedPersonEnd> personEnds) {
        this.personEnds.addAll(personEnds);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePersonEnd(ReadOnlyAddressBook source) {
        personEnds.addAll(source.getPersonEndList().stream().map(JsonAdaptedPersonEnd::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PersonEnds} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPersonEnd jsonAdaptedPersonEnd : personEnds) {
            PersonEnd personEnd = jsonAdaptedPersonEnd.toModelType();
            if (addressBook.hasPersonEnd(personEnd)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON_END);
            }
            addressBook.addPersonEnd(personEnd);
        }
        return addressBook;
    }

}
