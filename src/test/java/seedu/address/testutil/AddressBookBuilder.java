package seedu.address.testutil;

import seedu.address.model.PersonRegisters;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PersonRegisters ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private PersonRegisters personRegisters;

    public AddressBookBuilder() {
        personRegisters = new PersonRegisters();
    }

    public AddressBookBuilder(PersonRegisters personRegisters) {
        this.personRegisters = personRegisters;
    }

    /**
     * Adds a new {@code Person} to the {@code PersonRegisters} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        personRegisters.addPerson(person);
        return this;
    }

    public PersonRegisters build() {
        return personRegisters;
    }
}
