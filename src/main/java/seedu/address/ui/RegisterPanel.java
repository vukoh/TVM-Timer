package seedu.address.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;


public class RegisterPanel extends UiPart<Region> {

    private static final String FXML = "RegisterPanel.fxml";

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField bibNumberTextField;

    @FXML
    private TextField teamNumberTextField;

    @FXML
    private TextField categoryTextField;

    private MainWindow mainWindow;

    public RegisterPanel(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        focusToDiaryName();
    }


    /**
     * Executes a RegisterCommand, based on the input provided in the GUI.
     */
    @FXML
    void executeInput() {
        String command = "register";
        String nameInput = " n/ " + nameTextField.getText();
        String bibNumberInput = " b/ " + bibNumberTextField.getText();
        String teamNumberInput = " t/ " + teamNumberTextField.getText();
        String categoryInput = " c/ " + categoryTextField.getText();
        mainWindow.executeGuiCommand(command + nameInput + bibNumberInput
                + teamNumberInput + categoryInput);
        clearAllInputFields();
    }

    /**
     * Method that auto-focuses on the DiaryName TextField
     */
    private void focusToDiaryName() {
        Platform.runLater(()-> nameTextField.requestFocus());
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
        nameTextField.clear();
        bibNumberTextField.clear();
        teamNumberTextField.clear();
        categoryTextField.clear();
    }
}
