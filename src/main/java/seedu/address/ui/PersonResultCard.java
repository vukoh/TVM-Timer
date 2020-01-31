package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Category;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;

/**
 * An UI component that displays information of a {@code PersonRegister}.
 */
public class PersonResultCard extends UiPart<Region> {

    private static final String FXML = "PersonResultListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on PersonRegisters level 4</a>
     */

    public final PersonResult person;

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
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label timeTaken;


    public PersonResultCard(PersonResult person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        bibNumber.setText("Bib Number: " + person.getBibNumber().bibNumber);
        teamNumber.setText("Team Number: " + person.getTeamNumber().teamNumber);
        category.setText("Category: " + person.getCategory().toString());
        startTime.setText("Time Started: " + person.getStartTime().toString());
        endTime.setText("Time Ended: " + person.getEndTime().toString());
        long durationInSeconds = person.getTimeTaken();
        String duration = String.format("%d:%02d:%02d", durationInSeconds / 3600,
                (durationInSeconds % 3600) / 60, (durationInSeconds % 60));
        timeTaken.setText("Total Time taken: " + duration);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonResultCard)) {
            return false;
        }

        // state check
        PersonResultCard card = (PersonResultCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
