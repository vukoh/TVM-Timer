package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;
import seedu.address.model.person.UniquePersonEndList;
import seedu.address.model.person.UniquePersonRegisterList;
import seedu.address.model.person.UniquePersonResultList;
import seedu.address.model.person.UniquePersonStartList;
import seedu.address.model.tag.Tag;
import seedu.address.model.time.EndTime;
import seedu.address.model.time.UniqueEndTimeList;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the AddressBook level
 * Duplicates are not allowed (by .isSamePersonRegister, .isSamePersonStart and .isSamePersonResult comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonRegisterList personRegisters;

    private final UniquePersonStartList personStarts;

    private final UniquePersonResultList personResults;

    private final UniquePersonEndList personEnds;

    private final UniqueEndTimeList endTimes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * PersonStart that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        personRegisters = new UniquePersonRegisterList();

        personStarts = new UniquePersonStartList();

        personResults = new UniquePersonResultList();

        personEnds = new UniquePersonEndList();

        endTimes = new UniqueEndTimeList();
    }

    public AddressBook() {}

    /**
     * Creates a AddressBook using the data in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Creates a AddressBook using the data in the {@code personRegistersToBeCopied}, {@code personStartsToBeCopied} and
     * {@code personResultsToBeCopied}
     */
    public AddressBook(ReadOnlyPersonRegisters personRegistersToBeCopied,
                         ReadOnlyPersonStarts personStartsToBeCopied,
                         ReadOnlyPersonResults personResultsToBeCopied, ReadOnlyPersonEnds personEndsToBeCopied,
                       ReadOnlyEndTimes endTimesToBeCopied) {
        this();
        resetData(personRegistersToBeCopied, personStartsToBeCopied, personResultsToBeCopied, personEndsToBeCopied,
                endTimesToBeCopied);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersonStarts(newData.getPersonStartList());
        setPersonRegisters(newData.getPersonRegisterList());
        setPersonResults(newData.getPersonResultList());
        setPersonEnds(newData.getPersonEndList());
        setEndTimes(newData.getEndTimeList());
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newPersonRegisters}, {@code newPersonStarts} and
     * {@code newPersonResults}.
     */
    public void resetData(ReadOnlyPersonRegisters newPersonRegisters,
                          ReadOnlyPersonStarts newPersonStarts,
                          ReadOnlyPersonResults newPersonResults, ReadOnlyPersonEnds newPersonEnds,
                          ReadOnlyEndTimes newEndTimes) {
        requireNonNull(newPersonRegisters);
        requireNonNull(newPersonStarts);
        requireNonNull(newPersonResults);
        requireNonNull(newPersonEnds);
        requireNonNull(newEndTimes);

        setPersonRegisters(newPersonRegisters.getPersonRegisterList());
        setPersonStarts(newPersonStarts.getPersonStartList());
        setPersonResults(newPersonResults.getPersonResultList());
        setPersonEnds(newPersonEnds.getPersonEndList());
        setEndTimes(newEndTimes.getEndTimeList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && personRegisters.equals(((AddressBook) other).personRegisters)
                && personStarts.equals(((AddressBook) other).personStarts)
                && personResults.equals(((AddressBook) other).personResults)
                && personEnds.equals(((AddressBook) other).personEnds)
                && endTimes.equals(((AddressBook) other).endTimes));
    }

    @Override
    public String toString() {
        return personRegisters.asUnmodifiableObservableList().size() + " personRegisters" + "\n"
                + personStarts.asUnmodifiableObservableList().size() + " personStarts" + "\n"
                + personResults.asUnmodifiableObservableList().size() + " personResults" + "\n"
                + personEnds.asUnmodifiableObservableList().size() + " personEnds" + "\n"
                + endTimes.asUnmodifiableObservableList().size() + " endTimes";
        // TODO: refine later
    }

    @Override
    public int hashCode() {
        return Objects.hash(personRegisters, personStarts, personResults, personEnds, endTimes);
    }

    //=============================PersonRegister tools====================================================

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePersonRegister(PersonRegister key) {
        personRegisters.remove(key);
    }

    /**
     * Returns true if a personRegister with the same identity as {@code personRegister} exists in the application.
     */
    public boolean hasPersonRegister(PersonRegister personRegister) {
        requireNonNull(personRegister);
        return personRegisters.contains(personRegister);
    }

    /**
     * Adds a personRegister to the application.
     * The flashcard must not already exist in the application.
     */
    public void addPersonRegister(PersonRegister personRegister) {
        personRegisters.add(personRegister);
    }

    public ObservableList<PersonRegister> getPersonRegisterList() {
        return personRegisters.asUnmodifiableObservableList();
    }

    /**
     * Replaces the contents of the personRegister list with {@code personRegisters}.
     * {@code personRegisters} must not contain duplicate personRegisters.
     */
    public void setPersonRegisters(List<PersonRegister> personRegisters) {
        this.personRegisters.setPersonRegisters(personRegisters);
    }

    /**
     * Replaces the given personRegister {@code target} in the list with {@code editedPersonRegister}.
     * {@code target} must exist in the address book.
     * The personRegister identity of {@code editedPersonRegister} must not be the same as another existing
     * personRegister in the address book.
     */
    public void setPersonRegister(PersonRegister target, PersonRegister editedPersonRegister) {
        requireNonNull(editedPersonRegister);

        personRegisters.setPersonRegister(target, editedPersonRegister);
    }

    //=============================PersonStart tools====================================================

    /**
     * Returns true if a personStart with the same identity as {@code personStart} exists in the address book.
     */
    public boolean hasPersonStart(PersonStart personStart) {
        requireNonNull(personStart);
        return personStarts.contains(personStart);
    }

    /**
     * Adds a personStart to the address book.
     * The personStart must not already exist in the address book.
     */
    public void addPersonStart(PersonStart personStart) {
        personStarts.add(personStart);
    }

    /**
     * Replaces the given personStart {@code target} in the list with {@code editedPersonStart}.
     * {@code target} must exist in the address book.
     * The personStart identity of {@code editedPersonStart} must not be the same as another existing personStart in
     * the address book.
     */
    public void setPersonStart(PersonStart target, PersonStart editedPersonStart) {
        requireNonNull(editedPersonStart);

        personStarts.setPersonStart(target, editedPersonStart);
    }

    /**
     * Replaces the contents of the personStart list with {@code personStarts}.
     * {@code personStarts} must not contain duplicate personStarts.
     */
    public void setPersonStarts(List<PersonStart> personStarts) {
        this.personStarts.setPersonStarts(personStarts);
    }


    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePersonStart(PersonStart key) {
        personStarts.remove(key);
    }
    //// util methods

    @Override
    public ObservableList<PersonStart> getPersonStartList() {
        return personStarts.asUnmodifiableObservableList();
    }

    //=============================PersonResult tools====================================================

    /**
     * Adds a personResult to the address book.
     * The personResult must not already exist in the address book.
     */
    public void addPersonResult(PersonResult personResult) {
        personResults.add(personResult);
    }

    /**
     * Deletes a personResult to the address book.
     * The personResult must already exist in the address book.
     */
    public void removePersonResult(PersonResult personResult) {
        personResults.remove(personResult);
    }

    /**
     * Checks if the list of personResults contains this personResult
     */
    public boolean hasPersonResult(PersonResult personResult) {
        requireNonNull(personResult);
        return personResults.contains(personResult);
    }

    /**
     * Replaces the contents of the personResult list with {@code personResults}.
     * {@code personResults} must not contain duplicate personResults.
     */
    public void setPersonResults(List<PersonResult> personResults) {
        this.personResults.setPersonResults(personResults);
    }

    /**
     * Replaces the given personResult {@code target} in the list with {@code editedPersonResult}.
     * {@code target} must exist in the address book application.
     * The personResult identity of {@code editedPersonResult}
     * must not be the same as another existing personResult in the address book application.
     */
    public void setPersonResult(PersonResult target, PersonResult editedPersonResult) {
        requireNonNull(editedPersonResult);

        personResults.setPersonResult(target, editedPersonResult);
    }

    @Override
    public ObservableList<PersonResult> getPersonResultList() {
        return personResults.asUnmodifiableObservableList();
    }

    //=============================PersonEnd tools====================================================

    /**
     * Adds a personEnd to the address book.
     * The personEnd must not already exist in the address book.
     */
    public void addPersonEnd(PersonEnd personEnd) {
        personEnds.add(personEnd);
    }

    /**
     * Deletes a personEnd to the address book.
     * The personEnd must already exist in the address book.
     */
    public void removePersonEnd(PersonEnd personEnd) {
        personEnds.remove(personEnd);
    }

    /**
     * Checks if the list of personEnds contains this personEnd
     */
    public boolean hasPersonEnd(PersonEnd personEnd) {
        requireNonNull(personEnd);
        return personEnds.contains(personEnd);
    }

    /**
     * Replaces the contents of the personEnd list with {@code personEnds}.
     * {@code personEnds} must not contain duplicate personEnds.
     */
    public void setPersonEnds(List<PersonEnd> personEnds) {
        this.personEnds.setPersonEnds(personEnds);
    }

    /**
     * Replaces the given personEnd {@code target} in the list with {@code editedPersonEnd}.
     * {@code target} must exist in the address book application.
     * The personEnd identity of {@code editedPersonEnd}
     * must not be the same as another existing personEnd in the address book application.
     */
    public void setPersonEnd(PersonEnd target, PersonEnd editedPersonEnd) {
        requireNonNull(editedPersonEnd);

        personEnds.setPersonEnd(target, editedPersonEnd);
    }

    @Override
    public ObservableList<PersonEnd> getPersonEndList() {
        return personEnds.asUnmodifiableObservableList();
    }

    //=============================EndTime tools====================================================

    /**
     * Adds an endTime to the address book.
     * The endTime must not already exist in the address book.
     */
    public void addEndTime(EndTime endTime) {
        endTimes.add(endTime);
    }

    /**
     * Deletes an endTime to the address book.
     * The endTime must already exist in the address book.
     */
    public void removeEndTime(EndTime endTime) {
        endTimes.remove(endTime);
    }

    /**
     * Checks if the list of endTimes contains this endTime
     */
    public boolean hasEndTime(EndTime endTime) {
        requireNonNull(endTime);
        return endTimes.contains(endTime);
    }

    /**
     * Replaces the contents of the endTime list with {@code endTimes}.
     * {@code endTimes} must not contain duplicate endTimes.
     */
    public void setEndTimes(List<EndTime> endTimes) {
        this.endTimes.setEndTimes(endTimes);
    }

    /**
     * Replaces the given endTime {@code target} in the list with {@code editedEndTime}.
     * {@code target} must exist in the address book application.
     * The endTime identity of {@code editedEndTime}
     * must not be the same as another existing endTime in the address book application.
     */
    public void setEndTime(EndTime target, EndTime editedEndTime) {
        requireNonNull(editedEndTime);

        endTimes.setEndTime(target, editedEndTime);
    }

    @Override
    public ObservableList<EndTime> getEndTimeList() {
        return endTimes.asUnmodifiableObservableList();
    }

}
