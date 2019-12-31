package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.PersonRegister;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersonRegisters personRegisters;
    private final UserPrefs userPrefs;
    private final FilteredList<PersonRegister> filteredPersonRegisters;

    /**
     * Initializes a ModelManager with the given personRegisters and userPrefs.
     */
    public ModelManager(ReadOnlyPersonRegisters addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.personRegisters = new PersonRegisters(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersonRegisters = new FilteredList<>(this.personRegisters.getPersonRegisterList());
    }

    public ModelManager() {
        this(new PersonRegisters(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== PersonRegisters ================================================================================

    @Override
    public void setPersonRegisters(ReadOnlyPersonRegisters personRegisters) {
        this.personRegisters.resetData(personRegisters);
    }

    @Override
    public ReadOnlyPersonRegisters getPersonRegisters() {
        return personRegisters;
    }

    @Override
    public boolean hasPersonRegister(PersonRegister person) {
        requireNonNull(person);
        return personRegisters.hasPersonRegister(person);
    }

    @Override
    public void deletePersonRegister(PersonRegister target) {
        personRegisters.removePerson(target);
    }

    @Override
    public void addPersonRegister(PersonRegister person) {
        personRegisters.addPersonRegister(person);
        updateFilteredPersonRegisterList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPersonRegister(PersonRegister target, PersonRegister editedPerson) {
        requireAllNonNull(target, editedPerson);

        personRegisters.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<PersonRegister> getFilteredPersonRegisterList() {
        return filteredPersonRegisters;
    }

    @Override
    public void updateFilteredPersonRegisterList(Predicate<PersonRegister> predicate) {
        requireNonNull(predicate);
        filteredPersonRegisters.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return personRegisters.equals(other.personRegisters)
                && userPrefs.equals(other.userPrefs)
                && filteredPersonRegisters.equals(other.filteredPersonRegisters);
    }

}
