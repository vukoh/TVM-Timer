package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonStart;

/**
 * Unmodifiable view of PersonStarts
 */
public interface ReadOnlyPersonStarts {

    /**
     * Returns an unmodifiable view of the personStarts list.
     * This list will not contain any duplicate personStarts.
     */
    ObservableList<PersonStart> getPersonStartList();

}
