package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;
import seedu.address.model.time.EndTime;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook extends ReadOnlyPersonRegisters, ReadOnlyPersonStarts, ReadOnlyPersonResults,
        ReadOnlyPersonEnds, ReadOnlyEndTimes {


    ObservableList<PersonRegister> getPersonRegisterList();
    ObservableList<PersonStart> getPersonStartList();
    ObservableList<PersonResult> getPersonResultList();
    ObservableList<PersonEnd> getPersonEndList();
    ObservableList<EndTime> getEndTimeList();

}
