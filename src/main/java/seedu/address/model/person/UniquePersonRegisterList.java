package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonRegisterException;
import seedu.address.model.person.exceptions.PersonRegisterNotFoundException;

/**
 * A list of personRegisters that enforces uniqueness between its elements and does not allow nulls.
 * A personRegister is considered unique by comparing using {@code PersonRegister#equals(Object)}.
 * As such, adding and updating of persons uses PersonRegister#isSamePersonRegister(PersonRegister) for equality so
 * as to ensure that the person being added or updated is unique in terms of identity in the UniquePersonRegisterList.
 * However, the removal of a person uses PersonRegister#equals(Object) so as to ensure that the person with exactly
 * the same fields will be removed. Supports a minimal set of list operations.
 * @see PersonRegister#equals(Object)
 */
public class UniquePersonRegisterList implements Iterable<PersonRegister> {

    private final ObservableList<PersonRegister> internalList = FXCollections.observableArrayList();
    private final ObservableList<PersonRegister> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent personRegister as the given argument.
     */
    public boolean contains(PersonRegister toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a personRegister to the list.
     * The personRegister must not already exist in the list.
     */
    public void add(PersonRegister toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonRegisterException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the personRegister {@code target} in the list with {@code editedPersonRegister}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPersonRegister} must not be the same as another existing personRegister in
     * the list.
     */
    public void setPersonRegister(PersonRegister target, PersonRegister editedPersonRegister) {
        requireAllNonNull(target, editedPersonRegister);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonRegisterNotFoundException();
        }

        if (!target.equals(editedPersonRegister) && contains(editedPersonRegister)) {
            throw new DuplicatePersonRegisterException();
        }

        internalList.set(index, editedPersonRegister);
    }

    /**
     * Removes the equivalent personRegister from the list.
     * The personRegister must exist in the list.
     */
    public void remove(PersonRegister toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonRegisterNotFoundException();
        }
    }

    public void setPersonRegisters(UniquePersonRegisterList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code personRegisters}.
     * {@code personRegisters} must not contain duplicate personRegisters.
     */
    public void setPersonRegisters(List<PersonRegister> personRegisters) {
        requireAllNonNull(personRegisters);
        if (!personRegistersAreUnique(personRegisters)) {
            throw new DuplicatePersonRegisterException();
        }

        internalList.setAll(personRegisters);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<PersonRegister> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<PersonRegister> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonRegisterList // instanceof handles nulls
                && internalList.equals(((UniquePersonRegisterList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code personRegisters} contains only unique personRegisters.
     */
    private boolean personRegistersAreUnique(List<PersonRegister> personRegisters) {
        for (int i = 0; i < personRegisters.size() - 1; i++) {
            for (int j = i + 1; j < personRegisters.size(); j++) {
                if (personRegisters.get(i).equals(personRegisters.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
