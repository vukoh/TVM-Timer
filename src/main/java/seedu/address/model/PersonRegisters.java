package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.UniquePersonRegisterList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class PersonRegisters implements ReadOnlyPersonRegisters {

    private final UniquePersonRegisterList personRegisters;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        personRegisters = new UniquePersonRegisterList();
    }

    public PersonRegisters() {}

    /**
     * Creates an PersonRegisters using the Persons in the {@code toBeCopied}
     */
    public PersonRegisters(ReadOnlyPersonRegisters toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersonRegisters(List<PersonRegister> persons) {
        this.personRegisters.setPersonRegisters(persons);
    }

    /**
     * Resets the existing data of this {@code PersonRegisters} with {@code newData}.
     */
    public void resetData(ReadOnlyPersonRegisters newData) {
        requireNonNull(newData);

        setPersonRegisters(newData.getPersonRegisterList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPersonRegister(PersonRegister person) {
        requireNonNull(person);
        return personRegisters.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPersonRegister(PersonRegister p) {
        personRegisters.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(PersonRegister target, PersonRegister editedPerson) {
        requireNonNull(editedPerson);

        personRegisters.setPersonRegister(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code PersonRegisters}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(PersonRegister key) {
        personRegisters.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return personRegisters.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<PersonRegister> getPersonRegisterList() {
        return personRegisters.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonRegisters // instanceof handles nulls
                && personRegisters.equals(((PersonRegisters) other).personRegisters));
    }

    @Override
    public int hashCode() {
        return personRegisters.hashCode();
    }
}
