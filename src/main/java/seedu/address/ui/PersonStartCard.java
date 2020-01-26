package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.person.PersonStart;

public class PersonStartCard extends UiPart<Region> {

    private static final String FXML = "PersonStartListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PersonRegisters level 4</a>
     */

    public final PersonStart personStart;

    @FXML
    private Label bibNumber;

    @FXML
    private Label startTime;

    @FXML
    private Label id;

    public PersonStartCard(PersonStart person, int displayedIndex) {
        super(FXML);
        this.personStart = person;
        id.setText(displayedIndex + ". ");
        bibNumber.setText("Bib Number: " + personStart.getBibNumber().toString());
        startTime.setText("Start Time: " + personStart.getStartTime().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonStartCard)) {
            return false;
        }

        // state check
        PersonStartCard card = (PersonStartCard) other;
        return id.getText().equals(card.id.getText())
                && personStart.equals(card.personStart);
    }
}
