package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.PersonRegisters;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.PersonRegister;

/**
 * An Immutable PersonRegisters class that is serializable to JSON format.
 */
@JsonRootName(value = "personregisters")
class JsonSerializablePersonRegister {

    public static final String MESSAGE_DUPLICATE_PERSON_REGISTER = "PersonRegister list contains duplicate " +
            "personRegister(s).";

    private final List<JsonAdaptedPersonRegister> personRegisters = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializablePersonRegister} with the given personRegisters.
     */
    @JsonCreator
    public JsonSerializablePersonRegister(@JsonProperty("personregisters") List<JsonAdaptedPersonRegister> personRegisters) {
        this.personRegisters.addAll(personRegisters);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializablePersonRegister(ReadOnlyAddressBook source) {
        personRegisters.addAll(source.getPersonRegisterList().stream().map(JsonAdaptedPersonRegister::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code PersonRegisters} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType(AddressBook addressBook) throws IllegalValueException {
        for (JsonAdaptedPersonRegister jsonAdaptedPersonRegister : personRegisters) {
            PersonRegister personRegister = jsonAdaptedPersonRegister.toModelType();
            if (addressBook.hasPersonRegister(personRegister)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON_REGISTER);
            }
            addressBook.addPersonRegister(personRegister);
        }
        return addressBook;
    }

}
