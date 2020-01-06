package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyPersonRegisters;
import seedu.address.model.ReadOnlyPersonResults;
import seedu.address.model.ReadOnlyPersonStarts;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAddressBookStorage implements AddressBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path personRegisterFilePath;
    private Path personStartFilePath;
    private Path personResultFilePath;


    public JsonAddressBookStorage(Path personRegisterFilePath, Path personStartFilePath, Path personResultFilePath) {
        requireNonNull(personRegisterFilePath);
        requireNonNull(personStartFilePath);
        requireNonNull(personResultFilePath);
        this.personRegisterFilePath = personRegisterFilePath;
        this.personStartFilePath = personStartFilePath;
        this.personResultFilePath = personResultFilePath;
    }

    public Path getPersonRegisterFilePath() {
        return personRegisterFilePath;
    }

    public Path getPersonStartFilePath() {
        return personStartFilePath;
    }

    public Path getPersonResultFilePath() {
        return personResultFilePath;
    }

    @Override
    public Optional<ReadOnlyPersonRegisters> readPersonRegisters() throws DataConversionException {
        return readPersonRegisters(personRegisterFilePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPersonRegisters> readPersonRegisters(Path personRegisterFilePath) throws DataConversionException {
        requireNonNull(personRegisterFilePath);

        Optional<JsonSerializablePersonRegister> jsonPersonRegister;
        try {
            jsonPersonRegister = JsonUtil.readJsonFile(
                    personRegisterFilePath, JsonSerializablePersonRegister.class);
        } catch (DataConversionException ex) {
            throw new DataConversionException(ex);
        }
        if (!jsonPersonRegister.isPresent()) {
            return Optional.empty();
        } else {
            try {
                AddressBook addressBookWithPersonRegisters = new AddressBook();
                jsonPersonRegister.get().toModelType(addressBookWithPersonRegisters);
                return Optional.of(addressBookWithPersonRegisters);
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + personRegisterFilePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }
    }

    @Override
    public Optional<ReadOnlyPersonStarts> readPersonStarts() throws DataConversionException {
        return readPersonStarts(personStartFilePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPersonStarts> readPersonStarts(Path personStartFilePath) throws DataConversionException {
        requireNonNull(personStartFilePath);

        Optional<JsonSerializablePersonStart> jsonPersonStart;
        try {
            jsonPersonStart = JsonUtil.readJsonFile(
                    personStartFilePath, JsonSerializablePersonStart.class);
        } catch (DataConversionException ex) {
            throw new DataConversionException(ex);
        }
        if (!jsonPersonStart.isPresent()) {
            return Optional.empty();
        } else {
            try {
                AddressBook addressBookWithPersonStarts = new AddressBook();
                jsonPersonStart.get().toModelType(addressBookWithPersonStarts);
                return Optional.of(addressBookWithPersonStarts);
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + personStartFilePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }
    }

    @Override
    public Optional<ReadOnlyPersonResults> readPersonResults() throws DataConversionException {
        return readPersonResults(personResultFilePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPersonResults> readPersonResults(Path personResultFilePath) throws DataConversionException {
        requireNonNull(personResultFilePath);

        Optional<JsonSerializablePersonResult> jsonPersonResult;
        try {
            jsonPersonResult = JsonUtil.readJsonFile(
                    personResultFilePath, JsonSerializablePersonResult.class);
        } catch (DataConversionException ex) {
            throw new DataConversionException(ex);
        }
        if (!jsonPersonResult.isPresent()) {
            return Optional.empty();
        } else {
            try {
                AddressBook addressBookWithPersonResults = new AddressBook();
                jsonPersonResult.get().toModelType(addressBookWithPersonResults);
                return Optional.of(addressBookWithPersonResults);
            } catch (IllegalValueException ive) {
                logger.info("Illegal values found in " + personResultFilePath + ": " + ive.getMessage());
                throw new DataConversionException(ive);
            }
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, personRegisterFilePath, personStartFilePath, personResultFilePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path personRegisterFilePath,
                                Path personStartFilePath, Path personResultFilePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(personRegisterFilePath);
        requireNonNull(personStartFilePath);
        requireNonNull(personResultFilePath);


        FileUtil.createIfMissing(personRegisterFilePath);
        FileUtil.createIfMissing(personStartFilePath);
        FileUtil.createIfMissing(personResultFilePath);

        JsonUtil.saveJsonFile(new JsonSerializablePersonRegister(addressBook), personRegisterFilePath);
        JsonUtil.saveJsonFile(new JsonSerializablePersonStart(addressBook), personStartFilePath);
        JsonUtil.saveJsonFile(new JsonSerializablePersonResult(addressBook), personResultFilePath);

    }

}
