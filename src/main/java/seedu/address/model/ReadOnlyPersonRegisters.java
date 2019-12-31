package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of PersonRegisters
 */
public interface ReadOnlyPersonRegisters {

    /**
     * Returns an unmodifiable view of the personRegisters list.
     * This list will not contain any duplicate personRegisters.
     */
    ObservableList<Person> getPersonRegisterList();

}
