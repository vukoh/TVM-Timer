package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PersonRegisters;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPersonEnds;
import seedu.address.model.ReadOnlyPersonRegisters;
import seedu.address.model.ReadOnlyPersonResults;
import seedu.address.model.ReadOnlyPersonStarts;

/**
 * Represents a storage for {@link PersonRegisters}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPersonRegisterFilePath();
    Path getPersonStartFilePath();
    Path getPersonResultFilePath();
    Path getPersonEndFilePath();

    /**
     * Returns PersonRegisters data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPersonRegisters> readPersonRegisters() throws DataConversionException, IOException;

    Optional<ReadOnlyPersonRegisters> readPersonRegisters(Path personRegisterFilePath) throws DataConversionException,
            IOException;

    Optional<ReadOnlyPersonStarts> readPersonStarts() throws DataConversionException, IOException;

    Optional<ReadOnlyPersonStarts> readPersonStarts(Path personStartFilePath) throws DataConversionException,
            IOException;

    Optional<ReadOnlyPersonResults> readPersonResults() throws DataConversionException, IOException;

    Optional<ReadOnlyPersonResults> readPersonResults(Path personResultFilePath) throws DataConversionException,
            IOException;

    Optional<ReadOnlyPersonEnds> readPersonEnds() throws DataConversionException, IOException;

    Optional<ReadOnlyPersonEnds> readPersonEnds(Path personEndFilePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyAddressBook)
     */
    void saveAddressBook(ReadOnlyAddressBook addressBook, Path personRegisterFilePath, Path personStartFilePath,
                         Path personResultFilePath, Path personEndFilePath) throws IOException;

}
