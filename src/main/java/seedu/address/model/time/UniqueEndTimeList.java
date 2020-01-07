package seedu.address.model.time;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.time.exceptions.DuplicateEndTimeException;
import seedu.address.model.time.exceptions.EndTimeNotFoundException;

/**
 * A list of endTimes that enforces uniqueness between its elements and does not allow nulls.
 * A endTime is considered unique by comparing using {@code EndTime#equals(Object)}.
 * As such, adding and updating of persons uses EndTime#isSameEndTime(EndTime) for equality so
 * as to ensure that the person being added or updated is unique in terms of identity in the UniqueEndTimeList.
 * However, the removal of a person uses EndTime#equals(Object) so as to ensure that the person with exactly
 * the same fields will be removed. Supports a minimal set of list operations.
 * @see EndTime#equals(Object)
 */
public class UniqueEndTimeList implements Iterable<EndTime> {

    private final ObservableList<EndTime> internalList = FXCollections.observableArrayList();
    private final ObservableList<EndTime> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent endTime as the given argument.
     */
    public boolean contains(EndTime toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a endTime to the list.
     * The endTime must not already exist in the list.
     */
    public void add(EndTime toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateEndTimeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the endTime {@code target} in the list with {@code editedEndTime}.
     * {@code target} must exist in the list.
     * The endTime identity of {@code editedEndTime} must not be the same as another existing endTime in
     * the list.
     */
    public void setEndTime(EndTime target, EndTime editedEndTime) {
        requireAllNonNull(target, editedEndTime);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new EndTimeNotFoundException();
        }

        if (!target.equals(editedEndTime) && contains(editedEndTime)) {
            throw new DuplicateEndTimeException();
        }

        internalList.set(index, editedEndTime);
    }

    /**
     * Removes the equivalent endTime from the list.
     * The endTime must exist in the list.
     */
    public void remove(EndTime toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EndTimeNotFoundException();
        }
    }

    public void setEndTimes(UniqueEndTimeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code endTimes}.
     * {@code endTimes} must not contain duplicate endTimes.
     */
    public void setEndTimes(List<EndTime> endTimes) {
        requireAllNonNull(endTimes);
        if (!endTimesAreUnique(endTimes)) {
            throw new DuplicateEndTimeException();
        }

        internalList.setAll(endTimes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<EndTime> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<EndTime> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueEndTimeList // instanceof handles nulls
                && internalList.equals(((UniqueEndTimeList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code endTimes} contains only unique endTimes.
     */
    private boolean endTimesAreUnique(List<EndTime> endTimes) {
        for (int i = 0; i < endTimes.size() - 1; i++) {
            for (int j = i + 1; j < endTimes.size(); j++) {
                if (endTimes.get(i).equals(endTimes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
