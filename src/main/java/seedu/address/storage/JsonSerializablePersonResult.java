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
import seedu.address.model.person.PersonResult;

/**
 * An Immutable PersonResults class that is serializable to JSON format.
 */
@JsonRootName(value = "personresults")
class JsonSerializablePersonResult {

    public static final String MESSAGE_DUPLICATE_PERSON_RESULT = "PersonResult list contains duplicate " +
            "personResult(s).";

    private final List<JsonAdaptedPersonResult> personResults = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonResult} with the given personResults.
     */
    @JsonCreator
    public JsonSerializablePersonResult(@JsonProperty("personresults") List<JsonAdaptedPersonResult> personResults) {
        this.personResults.addAll(personResults);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePersonResult(ReadOnlyAddressBook source) {
        personResults.addAll(source.getPersonResultList().stream().map(JsonAdaptedPersonResult::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPersonResult jsonAdaptedPersonResult : personResults) {
            PersonResult personResult = jsonAdaptedPersonResult.toModelType();
            if (addressBook.hasPersonResult(personResult)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON_RESULT);
            }
            addressBook.addPersonResult(personResult);
        }
        return addressBook;
    }

}
