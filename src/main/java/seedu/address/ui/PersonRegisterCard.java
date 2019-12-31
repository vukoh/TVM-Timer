package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.PersonRegister;

/**
 * An UI component that displays information of a {@code PersonRegister}.
 */
public class PersonRegisterCard extends UiPart<Region> {

    private static final String FXML = "PersonRegisterListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final PersonRegister person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label bibNumber;
    @FXML
    private Label teamNumber;
    @FXML
    private Label category;

    public PersonRegisterCard(PersonRegister person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        bibNumber.setText(person.getBibNumber().bibNumber);
        category.setText(person.getCategory().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonRegisterCard)) {
            return false;
        }

        // state check
        PersonRegisterCard card = (PersonRegisterCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
