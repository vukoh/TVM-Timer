package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonEndException;
import seedu.address.model.person.exceptions.PersonEndNotFoundException;

/**
 * A list of personEnds that enforces uniqueness between its elements and does not allow nulls.
 * A personEnd is considered unique by comparing using {@code PersonEnd#equals(Object)}.
 * As such, adding and updating of persons uses PersonEnd#isSamePersonEnd(PersonEnd) for equality so
 * as to ensure that the person being added or updated is unique in terms of identity in the UniquePersonEndList.
 * However, the removal of a person uses PersonEnd#equals(Object) so as to ensure that the person with exactly
 * the same fields will be removed. Supports a minimal set of list operations.
 * @see PersonEnd#equals(Object)
 */
public class UniquePersonEndList implements Iterable<PersonEnd> {

    private final ObservableList<PersonEnd> internalList = FXCollections.observableArrayList();
    private final ObservableList<PersonEnd> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent personEnd as the given argument.
     */
    public boolean contains(PersonEnd toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a personEnd to the list.
     * The personEnd must not already exist in the list.
     */
    public void add(PersonEnd toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonEndException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the personEnd {@code target} in the list with {@code editedPersonEnd}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPersonEnd} must not be the same as another existing personEnd in
     * the list.
     */
    public void setPersonEnd(PersonEnd target, PersonEnd editedPersonEnd) {
        requireAllNonNull(target, editedPersonEnd);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonEndNotFoundException();
        }

        if (!target.equals(editedPersonEnd) && contains(editedPersonEnd)) {
            throw new DuplicatePersonEndException();
        }

        internalList.set(index, editedPersonEnd);
    }

    /**
     * Removes the equivalent personEnd from the list.
     * The personEnd must exist in the list.
     */
    public void remove(PersonEnd toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonEndNotFoundException();
        }
    }

    public void setPersonEnds(UniquePersonEndList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code personEnds}.
     * {@code personEnds} must not contain duplicate personEnds.
     */
    public void setPersonEnds(List<PersonEnd> personEnds) {
        requireAllNonNull(personEnds);
        if (!personEndsAreUnique(personEnds)) {
            throw new DuplicatePersonEndException();
        }

        internalList.setAll(personEnds);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonEnd> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PersonEnd> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonEndList // instanceof handles nulls
                && internalList.equals(((UniquePersonEndList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code personEnds} contains only unique personEnds.
     */
    private boolean personEndsAreUnique(List<PersonEnd> personEnds) {
        for (int i = 0; i < personEnds.size() - 1; i++) {
            for (int j = i + 1; j < personEnds.size(); j++) {
                if (personEnds.get(i).equals(personEnds.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
