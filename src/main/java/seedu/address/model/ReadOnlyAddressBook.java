package seedu.address.model;

import javafx.collections.ObservableList;
<<<<<<< HEAD
=======
import seedu.address.model.person.Person;
>>>>>>> ccc7c0a5cfe22e23f1b6ac6938de9ee2394ab516
import seedu.address.model.person.PersonRegister;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<PersonRegister> getPersonRegisterList();

    ObservableList<PersonRegister> getPersonRegisterList();


}
