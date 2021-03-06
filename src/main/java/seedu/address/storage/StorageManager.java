package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyEndTimes;
import seedu.address.model.ReadOnlyPersonEnds;
import seedu.address.model.ReadOnlyPersonRegisters;
import seedu.address.model.ReadOnlyPersonResults;
import seedu.address.model.ReadOnlyPersonStarts;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Filepath methods ==============================

    @Override
    public Path getPersonRegisterFilePath() {
        return addressBookStorage.getPersonRegisterFilePath();
    }

    @Override
    public Path getPersonStartFilePath() {
        return addressBookStorage.getPersonStartFilePath();
    }

    @Override
    public Path getPersonResultFilePath() {
        return addressBookStorage.getPersonResultFilePath();
    }

    @Override
    public Path getPersonEndFilePath() {
        return addressBookStorage.getPersonEndFilePath();
    }

    @Override
    public Path getEndTimeFilePath() {
        return addressBookStorage.getEndTimeFilePath();
    }


    // ================ Read methods ==============================

    @Override
    public Optional<ReadOnlyPersonRegisters> readPersonRegisters() throws DataConversionException, IOException {
        return addressBookStorage.readPersonRegisters(addressBookStorage.getPersonRegisterFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonRegisters> readPersonRegisters(Path personRegisterFilePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + personRegisterFilePath);
        return addressBookStorage.readPersonRegisters(personRegisterFilePath);
    }

    @Override
    public Optional<ReadOnlyPersonStarts> readPersonStarts() throws DataConversionException, IOException {
        return addressBookStorage.readPersonStarts(addressBookStorage.getPersonStartFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonStarts> readPersonStarts(Path personStartFilePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + personStartFilePath);
        return addressBookStorage.readPersonStarts(personStartFilePath);
    }

    @Override
    public Optional<ReadOnlyPersonResults> readPersonResults() throws DataConversionException, IOException {
        return addressBookStorage.readPersonResults(addressBookStorage.getPersonResultFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonResults> readPersonResults(Path personResultFilePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + personResultFilePath);
        return addressBookStorage.readPersonResults(personResultFilePath);
    }

    @Override
    public Optional<ReadOnlyPersonEnds> readPersonEnds() throws DataConversionException, IOException {
        return addressBookStorage.readPersonEnds(addressBookStorage.getPersonEndFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonEnds> readPersonEnds(Path personEndFilePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + personEndFilePath);
        return addressBookStorage.readPersonEnds(personEndFilePath);
    }

    @Override
    public Optional<ReadOnlyEndTimes> readEndTimes() throws DataConversionException, IOException {
        return addressBookStorage.readEndTimes(addressBookStorage.getEndTimeFilePath());
    }

    @Override
    public Optional<ReadOnlyEndTimes> readEndTimes(Path endTimeFilePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + endTimeFilePath);
        return addressBookStorage.readEndTimes(endTimeFilePath);
    }


    // ================ Save methods ==============================

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getPersonRegisterFilePath(), getPersonStartFilePath(),
                getPersonResultFilePath(), getPersonEndFilePath(), getEndTimeFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path personRegisterFilePath,
                                Path personStartFilePath, Path personResultFilePath, Path personEndFilePath,
                                Path endTimeFilePath) throws IOException {
        logger.fine("Attempting to write to data file: " + personRegisterFilePath + ", " + personStartFilePath + ", "
                + personResultFilePath + ", " + personEndFilePath + ", " + endTimeFilePath);
        addressBookStorage.saveAddressBook(addressBook, personRegisterFilePath, personStartFilePath,
                personResultFilePath, personEndFilePath, endTimeFilePath);
    }

}
