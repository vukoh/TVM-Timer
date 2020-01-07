package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonEnd;

/**
 * Unmodifiable view of PersonEnds
 */
public interface ReadOnlyPersonEnds {

    /**
     * Returns an unmodifiable view of the personEnds list.
     * This list will not contain any duplicate personEnds.
     */
    ObservableList<PersonEnd> getPersonEndList();

}
