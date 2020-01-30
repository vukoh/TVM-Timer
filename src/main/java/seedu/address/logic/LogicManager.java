package seedu.address.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;
import seedu.address.model.time.EndTime;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private static FunctionMode mode = FunctionMode.UNDEFINED;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            if (commandResult.isCalculateCommandResult()) {
                exportData();
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<PersonRegister> getFilteredPersonRegisterList() {
        return model.getFilteredPersonRegisterList();
    }

    @Override
    public ObservableList<PersonStart> getFilteredPersonStartList() {
        return model.getFilteredPersonStartList();
    }

    @Override
    public ObservableList<PersonResult> getFilteredPersonResultList() {
        return model.getFilteredPersonResultList();
    }

    @Override
    public ObservableList<EndTime> getFilteredEndTimeList() {
        return model.getFilteredEndTimeList();
    }

    @Override
    public ObservableList<PersonEnd> getFilteredPersonEndList() {
        return model.getFilteredPersonEndList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    public static FunctionMode getMode() {
        return mode;
    }

    public static void setMode(FunctionMode mode) {
        LogicManager.mode = mode;
    }

    // Place somewhere more OOP
    public void exportData() {
        try {
            JsonNode jsonTree = new ObjectMapper().readTree(new File("data/personResult.json"));
            CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
            JsonNode firstObject = jsonTree.findValue("personResults").elements().next();
            firstObject.fieldNames().forEachRemaining(csvSchemaBuilder::addColumn);
            CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
            CsvMapper csvMapper = new CsvMapper();
            FileUtil.createIfMissing(Paths.get("src/main/resources/personresults.csv"));
            csvMapper.writerFor(JsonNode.class)
                    .with(csvSchema)
                    .writeValue(new File("src/main/resources/personresults.csv"), jsonTree.findValue("personResults"));

        } catch (IOException e) {
            System.out.println("IOException when trying to read JSON file for conversion to CSV with message: " + e.getMessage());
        }
    }
}
