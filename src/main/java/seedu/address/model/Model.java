package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonRegister;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<PersonRegister> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<PersonRegister> PREDICATE_SHOW_ALL_PERSON_REGISTERS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setPersonRegisters(ReadOnlyPersonRegisters addressBook);

    /** Returns the PersonRegisters */
    ReadOnlyPersonRegisters getPersonRegisters();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPersonRegister(PersonRegister person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePersonRegister(PersonRegister target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPersonRegister(PersonRegister person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPersonRegister(PersonRegister target, PersonRegister editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<PersonRegister> getFilteredPersonRegisterList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonRegisterList(Predicate<PersonRegister> predicate);
}
