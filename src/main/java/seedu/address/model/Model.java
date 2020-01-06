package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<PersonRegister> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<PersonRegister> PREDICATE_SHOW_ALL_PERSON_REGISTERS = unused -> true;

    Predicate<PersonRegister> PREDICATE_SHOW_ALL_PERSON_STARTS = unused -> true;

    Predicate<PersonRegister> PREDICATE_SHOW_ALL_PERSON_RESULTS = unused -> true;

    // General stuff

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

    // Addressbook wide

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
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    // PersonRegister stuff

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    //void setPersonRegisters(ReadOnlyPersonRegisters addressBook);

    /** Returns the PersonRegisters */
    ReadOnlyPersonRegisters getPersonRegisters();

    /**
     * Returns true if a personRegister with the same identity as {@code personRegister} exists in the address book.
     */
    boolean hasPersonRegister(PersonRegister personRegister);

    /**
     * Deletes the given personRegister.
     * The personRegister must exist in the address book.
     */
    void deletePersonRegister(PersonRegister target);

    /**
     * Adds the given personRegister.
     * {@code personRegister} must not already exist in the address book.
     */
    void addPersonRegister(PersonRegister personRegister);

    /**
     * Replaces the given personRegister {@code target} with {@code editedPersonRegister}.
     * {@code target} must exist in the address book.
     * The personRegister identity of {@code editedPersonRegister} must not be the same as another existing
     * personRegister in the address book.
     */
    void setPersonRegister(PersonRegister target, PersonRegister editedPersonRegister);

    /** Returns an unmodifiable view of the filtered personRegister list */
    ObservableList<PersonRegister> getFilteredPersonRegisterList();

    /**
     * Updates the filter of the filtered personRegister list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonRegisterList(Predicate<PersonRegister> predicate);

    // PersonStart stuff

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    //void setPersonStarts(ReadOnlyPersonStarts addressBook);

    /** Returns the PersonRegisters */
    ReadOnlyPersonStarts getPersonStarts();

    /**
     * Returns true if a personStart with the same identity as {@code personStart} exists in the address book.
     */
    boolean hasPersonStart(PersonStart personStart);

    /**
     * Deletes the given personStart.
     * The person must exist in the address book.
     */
    void deletePersonStart(PersonStart target);

    /**
     * Adds the given personStart.
     * {@code personStart} must not already exist in the address book.
     */
    void addPersonStart(PersonStart personStart);

    /**
     * Replaces the given personStart {@code target} with {@code editedPersonStart}.
     * {@code target} must exist in the address book.
     * The personStart identity of {@code editedPersonStart} must not be the same as another existing personStart in
     * the address book.
     */
    void setPersonStart(PersonStart target, PersonStart editedPersonStart);

    /** Returns an unmodifiable view of the filtered personStart list */
    ObservableList<PersonStart> getFilteredPersonStartList();

    /**
     * Updates the filter of the filtered personStart list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonStartList(Predicate<PersonStart> predicate);

    // PersonResult stuff

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    //void setPersonResults(ReadOnlyPersonResults addressBook);

    /** Returns the PersonResults */
    ReadOnlyPersonResults getPersonResults();

    /**
     * Returns true if a personResult with the same identity as {@code personResult} exists in the address book.
     */
    boolean hasPersonResult(PersonResult personResult);

    /**
     * Deletes the given personResult.
     * The person must exist in the address book.
     */
    void deletePersonResult(PersonResult target);

    /**
     * Adds the given personResult.
     * {@code person} must not already exist in the address book.
     */
    void addPersonResult(PersonResult personResult);

    /**
     * Replaces the given personResult {@code target} with {@code editedPersonResult}.
     * {@code target} must exist in the address book.
     * The personResult identity of {@code editedPersonResult} must not be the same as another existing personResult in
     * the address book.
     */
    void setPersonResult(PersonResult target, PersonResult editedPersonResult);

    /** Returns an unmodifiable view of the filtered personResult list */
    ObservableList<PersonResult> getFilteredPersonResultList();

    /**
     * Updates the filter of the filtered personResult list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonResultList(Predicate<PersonResult> predicate);
}
