package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPersonEnds;
import seedu.address.model.ReadOnlyPersonRegisters;
import seedu.address.model.ReadOnlyPersonResults;
import seedu.address.model.ReadOnlyPersonStarts;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getPersonRegisterFilePath();

    @Override
    Path getPersonStartFilePath();

    @Override
    Path getPersonResultFilePath();

    @Override
    Path getPersonEndFilePath();

    @Override
    Optional<ReadOnlyPersonRegisters> readPersonRegisters() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyPersonStarts> readPersonStarts() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyPersonResults> readPersonResults() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyPersonEnds> readPersonEnds() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
