package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook extends ReadOnlyPersonRegisters, ReadOnlyPersonStarts, ReadOnlyPersonResults {


    ObservableList<PersonRegister> getPersonRegisterList();
    ObservableList<PersonStart> getPersonStartList();
    ObservableList<PersonResult> getPersonResultList();

}
