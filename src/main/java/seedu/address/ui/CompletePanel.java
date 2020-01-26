package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import seedu.address.model.person.PersonEnd;

public class CompletePanel extends UiPart<Region> {

    private static final String FXML = "CompletePanel.fxml";

    @FXML
    private TextField bibNumberField;

    @FXML
    private TextField positionField;

    @FXML
    private ListView<PersonEnd> personEndListView;

    private MainWindow mainWindow;

    public CompletePanel(MainWindow mainWindow, ObservableList<PersonEnd> personEndList) {
        super(FXML);
        this.mainWindow = mainWindow;
        personEndListView.setItems(personEndList);
        personEndListView.setCellFactory(personEndListView -> new PersonEndListViewCell());
    }


    /**
     * Executes a RegisterCommand, based on the input provided in the GUI.
     */
    @FXML
    void executeInput() {
        String command = "complete";
        String bibNumberInput = " b/" + bibNumberField.getText();
        String positionInput = " i/" + positionField.getText();
        mainWindow.executeGuiCommand(command + bibNumberInput + positionInput);
        clearAllInputFields();
    }

    @FXML
    void handleConfirmAction() {
        executeInput();
    }

    @FXML
    void handleCancelAction() {
        clearAllInputFields();
    }

    /**
     * Utility method that clear all inputs from the various text fields.
     */
    private void clearAllInputFields() {
        bibNumberField.clear();
        positionField.clear();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonEndListViewCell extends ListCell<PersonEnd> {
        @Override
        protected void updateItem(PersonEnd person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonEndCard(person).getRoot());
            }
        }
    }
}
