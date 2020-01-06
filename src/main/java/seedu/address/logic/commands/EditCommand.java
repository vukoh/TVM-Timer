package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSON_REGISTERS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.BibNumber;
import seedu.address.model.person.Category;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.team.TeamNumber;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the racer identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_BIB_NUMBER + "BIB NUMBER] "
            + "[" + PREFIX_TEAM_NUMBER + "TEAM NUMBER] "
            + "[" + PREFIX_CATEGORY + "CATEGORY] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "ULAR "
            + PREFIX_BIB_NUMBER + "16";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the register list.";

    private final Index index;
    private final EditPersonRegisterDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonRegisterDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonRegisterDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<PersonRegister> lastShownList = model.getFilteredPersonRegisterList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        PersonRegister personToEdit = lastShownList.get(index.getZeroBased());
        PersonRegister editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        if (!personToEdit.equals(editedPerson) && model.hasPersonRegister(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPersonRegister(personToEdit, editedPerson);
        model.updateFilteredPersonRegisterList(PREDICATE_SHOW_ALL_PERSON_REGISTERS);
        return new GlobalCommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static PersonRegister createEditedPerson(PersonRegister personToEdit, EditPersonRegisterDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        BibNumber updatedBibNumber = editPersonDescriptor.getBibNumber().orElse(personToEdit.getBibNumber());
        TeamNumber updatedTeamNumber = editPersonDescriptor.getTeamNumber().orElse(
                personToEdit.getTeamNumber());
        Category updatedCategory = editPersonDescriptor.getCategory().orElse(
                personToEdit.getCategory());

        return new PersonRegister(updatedName, updatedBibNumber, updatedTeamNumber, updatedCategory);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonRegisterDescriptor {

        private Name name;
        private BibNumber bibNumber;
        private TeamNumber teamNumber;
        private Category category;

        public EditPersonRegisterDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonRegisterDescriptor(EditPersonRegisterDescriptor toCopy) {
            setName(toCopy.name);
            setTeamNumber(toCopy.teamNumber);
            setBibNumber(toCopy.bibNumber);
            setCategory(toCopy.category);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, bibNumber, teamNumber,
                    category);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setBibNumber(BibNumber bibNumber) {
            this.bibNumber = bibNumber;
        }

        public Optional<BibNumber> getBibNumber() {
            return Optional.ofNullable(bibNumber);
        }

        public void setTeamNumber(TeamNumber teamNumber) {
            this.teamNumber = teamNumber;
        }

        public Optional<TeamNumber> getTeamNumber() {
            return Optional.ofNullable(teamNumber);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonRegisterDescriptor)) {
                return false;
            }

            // state check
            EditPersonRegisterDescriptor e = (EditPersonRegisterDescriptor) other;

            return getName().equals(e.getName())
                    && getBibNumber().equals(e.getBibNumber())
                    && getTeamNumber().equals(e.getTeamNumber())
                    && getCategory().equals(e.getCategory());
        }
    }
}
