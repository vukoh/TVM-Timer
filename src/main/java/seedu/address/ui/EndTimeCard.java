package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.time.EndTime;

public class EndTimeCard extends UiPart<Region> {

    private static final String FXML = "EndTimeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PersonRegisters level 4</a>
     */

    public final EndTime endTime;

    @FXML
    private Label endingTime;

    @FXML
    private Label id;

    public EndTimeCard(EndTime endTime, int displayedIndex) {
        super(FXML);
        this.endTime = endTime;
        id.setText(displayedIndex + ". ");
        endingTime.setText("End Time: " + endTime.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EndTimeCard)) {
            return false;
        }

        // state check
        EndTimeCard card = (EndTimeCard) other;
        return id.getText().equals(card.id.getText())
                && endTime.equals(card.endTime);
    }
}
