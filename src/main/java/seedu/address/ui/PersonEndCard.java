package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.PersonEnd;
import seedu.address.model.time.EndTime;

public class PersonEndCard extends UiPart<Region> {

    private static final String FXML = "PersonEndListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PersonRegisters level 4</a>
     */

    public final PersonEnd personEnd;

    @FXML
    private Label bibNumber;

    @FXML
    private Label position;

    public PersonEndCard(PersonEnd personEnd) {
        super(FXML);
        this.personEnd = personEnd;
        position.setText(personEnd.getIndex().toString() + ". ");
        bibNumber.setText("Bib Number: " + personEnd.getBibNumber().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonEndCard)) {
            return false;
        }

        // state check
        PersonEndCard card = (PersonEndCard) other;
        return position.getText().equals(card.position.getText())
                && personEnd.equals(card.personEnd);
    }
}
