package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonRegister;

/**
 * Unmodifiable view of PersonRegisters
 */
public interface ReadOnlyPersonRegisters {

    /**
     * Returns an unmodifiable view of the personRegisters list.
     * This list will not contain any duplicate personRegisters.
     */
    ObservableList<PersonRegister> getPersonRegisterList();

}
