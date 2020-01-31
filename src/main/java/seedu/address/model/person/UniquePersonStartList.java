package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonStartException;
import seedu.address.model.person.exceptions.PersonStartNotFoundException;

/**
 * A list of personStarts that enforces uniqueness between its elements and does not allow nulls.
 * A personStart is considered unique by comparing using {@code PersonStart#equals(Object)}.
 * As such, adding and updating of persons uses PersonStart#isSamePersonStart(PersonStart) for equality so
 * as to ensure that the person being added or updated is unique in terms of identity in the UniquePersonStartList.
 * However, the removal of a person uses PersonStart#equals(Object) so as to ensure that the person with exactly
 * the same fields will be removed. Supports a minimal set of list operations.
 * @see PersonStart#equals(Object)
 */
public class UniquePersonStartList implements Iterable<PersonStart> {

    private final ObservableList<PersonStart> internalList = FXCollections.observableArrayList();
    private final ObservableList<PersonStart> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent personStart as the given argument.
     */
    public boolean contains(PersonStart toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isDuplicateBibNumber);
    }

    /**
     * Adds a personStart to the list.
     * The personStart must not already exist in the list.
     */
    public void add(PersonStart toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonStartException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the personStart {@code target} in the list with {@code editedPersonStart}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPersonStart} must not be the same as another existing personStart in
     * the list.
     */
    public void setPersonStart(PersonStart target, PersonStart editedPersonStart) {
        requireAllNonNull(target, editedPersonStart);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonStartNotFoundException();
        }

        if (!target.equals(editedPersonStart) && contains(editedPersonStart)) {
            throw new DuplicatePersonStartException();
        }

        internalList.set(index, editedPersonStart);
    }

    /**
     * Removes the equivalent personStart from the list.
     * The personStart must exist in the list.
     */
    public void remove(PersonStart toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonStartNotFoundException();
        }
    }

    public void setPersonStarts(UniquePersonStartList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code personStarts}.
     * {@code personStarts} must not contain duplicate personStarts.
     */
    public void setPersonStarts(List<PersonStart> personStarts) {
        requireAllNonNull(personStarts);
        if (!personStartsAreUnique(personStarts)) {
            throw new DuplicatePersonStartException();
        }

        internalList.setAll(personStarts);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonStart> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PersonStart> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonStartList // instanceof handles nulls
                && internalList.equals(((UniquePersonStartList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code personStarts} contains only unique personStarts.
     */
    private boolean personStartsAreUnique(List<PersonStart> personStarts) {
        for (int i = 0; i < personStarts.size() - 1; i++) {
            for (int j = i + 1; j < personStarts.size(); j++) {
                if (personStarts.get(i).equals(personStarts.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
