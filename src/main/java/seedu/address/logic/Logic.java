package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;
import seedu.address.model.person.PersonStart;
import seedu.address.model.time.EndTime;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the PersonRegisters.
     *
     * @see seedu.address.model.Model#getPersonRegisters()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<PersonRegister> getFilteredPersonRegisterList();

    ObservableList<PersonStart> getFilteredPersonStartList();

    ObservableList<PersonResult> getFilteredPersonResultList();

    ObservableList<EndTime> getFilteredEndTimeList();

    ObservableList<PersonEnd> getFilteredPersonEndList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
