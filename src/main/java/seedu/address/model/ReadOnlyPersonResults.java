package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonResult;

/**
 * Unmodifiable view of PersonResults
 */
public interface ReadOnlyPersonResults {

    /**
     * Returns an unmodifiable view of the personResults list.
     * This list will not contain any duplicate personResults.
     */
    ObservableList<PersonResult> getPersonResultList();

}
