package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonResultException;
import seedu.address.model.person.exceptions.PersonResultNotFoundException;

/**
 * A list of personResults that enforces uniqueness between its elements and does not allow nulls.
 * A personResult is considered unique by comparing using {@code PersonResult#equals(Object)}.
 * As such, adding and updating of persons uses PersonResult#isSamePersonResult(PersonResult) for equality so
 * as to ensure that the person being added or updated is unique in terms of identity in the UniquePersonResultList.
 * However, the removal of a person uses PersonResult#equals(Object) so as to ensure that the person with exactly
 * the same fields will be removed. Supports a minimal set of list operations.
 * @see PersonResult#equals(Object)
 */
public class UniquePersonResultList implements Iterable<PersonResult> {

    private final ObservableList<PersonResult> internalList = FXCollections.observableArrayList();
    private final ObservableList<PersonResult> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent personResult as the given argument.
     */
    public boolean contains(PersonResult toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a personResult to the list.
     * The personResult must not already exist in the list.
     */
    public void add(PersonResult toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonResultException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the personResult {@code target} in the list with {@code editedPersonResult}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPersonResult} must not be the same as another existing personResult in
     * the list.
     */
    public void setPersonResult(PersonResult target, PersonResult editedPersonResult) {
        requireAllNonNull(target, editedPersonResult);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonResultNotFoundException();
        }

        if (!target.equals(editedPersonResult) && contains(editedPersonResult)) {
            throw new DuplicatePersonResultException();
        }

        internalList.set(index, editedPersonResult);
    }

    /**
     * Removes the equivalent personResult from the list.
     * The personResult must exist in the list.
     */
    public void remove(PersonResult toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonResultNotFoundException();
        }
    }

    public void setPersonResults(UniquePersonResultList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code personResults}.
     * {@code personResults} must not contain duplicate personResults.
     */
    public void setPersonResults(List<PersonResult> personResults) {
        requireAllNonNull(personResults);
        if (!personResultsAreUnique(personResults)) {
            throw new DuplicatePersonResultException();
        }

        internalList.setAll(personResults);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonResult> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PersonResult> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonResultList // instanceof handles nulls
                && internalList.equals(((UniquePersonResultList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code personResults} contains only unique personResults.
     */
    private boolean personResultsAreUnique(List<PersonResult> personResults) {
        for (int i = 0; i < personResults.size() - 1; i++) {
            for (int j = i + 1; j < personResults.size(); j++) {
                if (personResults.get(i).equals(personResults.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
