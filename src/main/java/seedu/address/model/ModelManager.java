package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;
import seedu.address.model.time.EndTime;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<PersonRegister> filteredPersonRegisters;
    private final FilteredList<PersonStart> filteredPersonStarts;
    private final FilteredList<PersonResult> filteredPersonResults;
    private final FilteredList<PersonEnd> filteredPersonEnds;
    private final FilteredList<EndTime> filteredEndTimes;

    /**
     * Initializes a ModelManager with the given personRegisters and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersonRegisters = new FilteredList<>(this.addressBook.getPersonRegisterList());
        filteredPersonStarts = new FilteredList<>(this.addressBook.getPersonStartList());
        filteredPersonResults = new FilteredList<>(this.addressBook.getPersonResultList());
        filteredPersonEnds = new FilteredList<>(this.addressBook.getPersonEndList());
        filteredEndTimes = new FilteredList<>(this.addressBook.getEndTimeList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    //=========== PersonRegister ================================================================================

    @Override
    public boolean hasPersonRegister(PersonRegister personRegister) {
        requireNonNull(personRegister);
        return addressBook.hasPersonRegister(personRegister);
    }

    @Override
    public void deletePersonRegister(PersonRegister target) {
        addressBook.removePersonRegister(target);
    }

    @Override
    public void addPersonRegister(PersonRegister personRegister) {
        addressBook.addPersonRegister(personRegister);
        updateFilteredPersonRegisterList(PREDICATE_SHOW_ALL_PERSON_REGISTERS);
    }

    @Override
    public void setPersonRegister(PersonRegister target, PersonRegister editedPersonRegister) {
        requireAllNonNull(target, editedPersonRegister);

        addressBook.setPersonRegister(target, editedPersonRegister);
    }

    @Override
    public ObservableList<PersonRegister> getPersonRegisters() {
        return addressBook.getPersonRegisterList();
    }

    //=========== PersonStart ================================================================================

    @Override
    public boolean hasPersonStart(PersonStart personStart) {
        requireNonNull(personStart);
        return addressBook.hasPersonStart(personStart);
    }

    @Override
    public void deletePersonStart(PersonStart target) {
        addressBook.removePersonStart(target);
    }

    @Override
    public void addPersonStart(PersonStart personStart) {
        addressBook.addPersonStart(personStart);
        updateFilteredPersonStartList(PREDICATE_SHOW_ALL_PERSON_STARTS);
    }

    @Override
    public void setPersonStart(PersonStart target, PersonStart editedPersonStart) {
        requireAllNonNull(target, editedPersonStart);

        addressBook.setPersonStart(target, editedPersonStart);
    }

    @Override
    public ObservableList<PersonStart> getPersonStarts() {
        return addressBook.getPersonStartList();
    }

    //=========== PersonResult ================================================================================

    @Override
    public boolean hasPersonResult(PersonResult personResult) {
        requireNonNull(personResult);
        return addressBook.hasPersonResult(personResult);
    }

    @Override
    public void deletePersonResult(PersonResult target) {
        addressBook.removePersonResult(target);
    }

    @Override
    public void addPersonResult(PersonResult personResult) {
        addressBook.addPersonResult(personResult);
        updateFilteredPersonResultList(PREDICATE_SHOW_ALL_PERSON_RESULTS);
    }

    @Override
    public void setPersonResult(PersonResult target, PersonResult editedPersonResult) {
        requireAllNonNull(target, editedPersonResult);

        addressBook.setPersonResult(target, editedPersonResult);
    }

    @Override
    public void setPersonResults(List<PersonResult> personResults) {
        addressBook.setPersonResults(personResults);
    }

    //=========== PersonEnd ================================================================================

    @Override
    public boolean hasPersonEnd(PersonEnd personEnd) {
        requireNonNull(personEnd);
        return addressBook.hasPersonEnd(personEnd);
    }

    @Override
    public void deletePersonEnd(PersonEnd target) {
        addressBook.removePersonEnd(target);
    }

    @Override
    public void addPersonEnd(PersonEnd personEnd) {
        addressBook.addPersonEnd(personEnd);
        updateFilteredPersonEndList(PREDICATE_SHOW_ALL_PERSON_ENDS);
    }

    @Override
    public void setPersonEnd(PersonEnd target, PersonEnd editedPersonEnd) {
        requireAllNonNull(target, editedPersonEnd);

        addressBook.setPersonEnd(target, editedPersonEnd);
    }

    @Override
    public ObservableList<PersonEnd> getPersonEnds() {
        return addressBook.getPersonEndList();
    }

    //=========== EndTime ================================================================================

    @Override
    public boolean hasEndTime(EndTime endTime) {
        requireNonNull(endTime);
        return addressBook.hasEndTime(endTime);
    }

    @Override
    public void deleteEndTime(EndTime target) {
        addressBook.removeEndTime(target);
    }

    @Override
    public void addEndTime(EndTime endTime) {
        addressBook.addEndTime(endTime);
        updateFilteredEndTimeList(PREDICATE_SHOW_ALL_END_TIMES);
    }

    @Override
    public void setEndTime(EndTime target, EndTime editedEndTime) {
        requireAllNonNull(target, editedEndTime);

        addressBook.setEndTime(target, editedEndTime);
    }

    @Override
    public ObservableList<EndTime> getEndTimes() {
        return addressBook.getEndTimeList();
    }

    //=========== Filtered Person List Accessors =============================================================

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
    public ObservableList<PersonStart> getFilteredPersonStartList() {
        return filteredPersonStarts;
    }

    @Override
    public void updateFilteredPersonStartList(Predicate<PersonStart> predicate) {
        requireNonNull(predicate);
        filteredPersonStarts.setPredicate(predicate);
    }

    @Override
    public ObservableList<PersonResult> getFilteredPersonResultList() {
        return filteredPersonResults;
    }

    @Override
    public void updateFilteredPersonResultList(Predicate<PersonResult> predicate) {
        requireNonNull(predicate);
        filteredPersonResults.setPredicate(predicate);
    }

    @Override
    public ObservableList<PersonEnd> getFilteredPersonEndList() {
        return filteredPersonEnds;
    }

    @Override
    public void updateFilteredPersonEndList(Predicate<PersonEnd> predicate) {
        requireNonNull(predicate);
        filteredPersonEnds.setPredicate(predicate);
    }

    @Override
    public ObservableList<EndTime> getFilteredEndTimeList() {
        return filteredEndTimes;
    }

    @Override
    public void updateFilteredEndTimeList(Predicate<EndTime> predicate) {
        requireNonNull(predicate);
        filteredEndTimes.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersonRegisters.equals(other.filteredPersonRegisters)
                && filteredPersonStarts.equals(other.filteredPersonStarts)
                && filteredPersonResults.equals(other.filteredPersonResults)
                && filteredPersonEnds.equals(other.filteredPersonEnds)
                && filteredEndTimes.equals(other.filteredEndTimes);
    }

}
