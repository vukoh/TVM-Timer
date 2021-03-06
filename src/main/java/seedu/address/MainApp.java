package seedu.address;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.*;
import seedu.address.model.PersonRegisters;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing application ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        AddressBookStorage addressBookStorage = new JsonAddressBookStorage(userPrefs.getPersonRegisterFilePath(),
                userPrefs.getPersonStartFilePath(), userPrefs.getPersonResultFilePath(),
                userPrefs.getPersonEndFilePath(), userPrefs.getEndTimeFilePath());
        storage = new StorageManager(addressBookStorage, userPrefsStorage);

        initLogging(config);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s address book and {@code userPrefs}. <br>
     * The data from the sample address book will be used instead if {@code storage}'s address book is not found,
     * or an empty address book will be used instead if errors occur when reading {@code storage}'s address book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {

        AddressBook initialData = new AddressBook();
        initialData = initModelManagerPersonRegisterHelper(storage, initialData);
        initialData = initModelManagerPersonStartHelper(storage, initialData);
        initialData = initModelManagerPersonResultHelper(storage, initialData);
        initialData = initModelManagerPersonEndHelper(storage, initialData);
        initialData = initModelManagerEndTimeHelper(storage, initialData);

        return new ModelManager(initialData, userPrefs);
    }

    //Change naming
    private AddressBook initModelManagerPersonRegisterHelper(Storage storage, AddressBook initialData) {
        Optional<ReadOnlyPersonRegisters> addressBookPersonRegistersOptional;
        try {
            addressBookPersonRegistersOptional = storage.readPersonRegisters();
            if (addressBookPersonRegistersOptional.isEmpty()) {
                logger.info("PersonRegisters data file not found. Will be starting with empty file");
            } else {
                initialData.setPersonRegisters(addressBookPersonRegistersOptional.get().getPersonRegisterList());
            }
        } catch (DataConversionException e) {
            logger.warning("PersonRegister data file not in the correct format. Will be starting with empty "
                    + "file and continue checking for PersonStart, PersonResult, PersonEnd and EndTime data files");
        } catch (IOException e) {
            logger.warning("Problem while reading from PersonRegister data file. Will be starting with empty "
                    + "file and continue checking for PersonStart, PersonResult, PersonEnd and EndTime data files");
        } finally {
            return initialData;
        }
    }

    private AddressBook initModelManagerPersonStartHelper(Storage storage, AddressBook initialData) {
        Optional<ReadOnlyPersonStarts> addressBookPersonStartsOptional;
        try {
            addressBookPersonStartsOptional = storage.readPersonStarts();
            if (addressBookPersonStartsOptional.isEmpty()) {
                logger.info("PersonStarts data file not found. Will be starting with empty file");
            } else {
                initialData.setPersonStarts(addressBookPersonStartsOptional.get().getPersonStartList());
            }
        } catch (DataConversionException e) {
            logger.warning("PersonStart data file not in the correct format. Will be starting with empty "
                    + "file and continue checking for PersonResult, PersonEnd and EndTime data file");
        } catch (IOException e) {
            logger.warning("Problem while reading from PersonStart data file. Will be starting with empty "
                    + "file and continue checking for PersonResult, PersonEnd and EndTime data file");
        } finally {
            return initialData;
        }
    }

    private AddressBook initModelManagerPersonResultHelper(Storage storage, AddressBook initialData) {
        Optional<ReadOnlyPersonResults> addressBookPersonResultsOptional;
        try {
            addressBookPersonResultsOptional = storage.readPersonResults();
            if (addressBookPersonResultsOptional.isEmpty()) {
                logger.info("PersonResults data file not found. Will be starting with empty file");
            } else {
                initialData.setPersonResults(addressBookPersonResultsOptional.get().getPersonResultList());
            }
        } catch (DataConversionException e) {
            logger.warning("PersonResults data file not in the correct format. Will be starting with empty "
                    + "file and continue checking for PersonEnd and EndTime data file");
        } catch (IOException e) {
            logger.warning("Problem while reading from PersonResult data file. Will be starting with empty "
                    + "file and continue checking for PersonEnd and EndTime data file");
        } finally {
            return initialData;
        }
    }

    private AddressBook initModelManagerPersonEndHelper(Storage storage, AddressBook initialData) {
        Optional<ReadOnlyPersonEnds> addressBookPersonEndsOptional;
        try {
            addressBookPersonEndsOptional = storage.readPersonEnds();
            if (addressBookPersonEndsOptional.isEmpty()) {
                logger.info("PersonEnds data file not found. Will be starting with empty file");
            } else {
                initialData.setPersonEnds(addressBookPersonEndsOptional.get().getPersonEndList());
            }
        } catch (DataConversionException e) {
            logger.warning("PersonEnds data file not in the correct format. Will be starting with empty "
                    + "file");
        } catch (IOException e) {
            logger.warning("Problem while reading from PersonEnd data file. Will be starting with empty "
                    + "file");
        } finally {
            return initialData;
        }
    }

    private AddressBook initModelManagerEndTimeHelper(Storage storage, AddressBook initialData) {
        Optional<ReadOnlyEndTimes> addressBookEndTimesOptional;
        try {
            addressBookEndTimesOptional = storage.readEndTimes();
            if (addressBookEndTimesOptional.isEmpty()) {
                logger.info("EndTimes data file not found. Will be starting with empty file");
            } else {
                initialData.setEndTimes(addressBookEndTimesOptional.get().getEndTimeList());
            }
        } catch (DataConversionException e) {
            logger.warning("EndTimes data file not in the correct format. Will be starting with empty "
                    + "file");
        } catch (IOException e) {
            logger.warning("Problem while reading from EndTime data file. Will be starting with empty "
                    + "file");
        } finally {
            return initialData;
        }
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataConversionException e) {
            logger.warning("Config file at " + configFilePathUsed + " is not in the correct format. "
                    + "Using default config properties");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using prefs file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataConversionException e) {
            logger.warning("UserPrefs file at " + prefsFilePath + " is not in the correct format. "
                    + "Using default user prefs");
            initializedPrefs = new UserPrefs();
        } catch (IOException e) {
            logger.warning("Problem while reading from the file. Will be starting with an empty PersonRegisters");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting PersonRegisters " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Address Book ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
