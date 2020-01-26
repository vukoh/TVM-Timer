package seedu.address.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonStart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class StartPanel extends UiPart<Region> {

    private static final String FXML = "StartPanel.fxml";
    ArrayList<TextField> fields = new ArrayList<>();

    @FXML
    private Label timer;

    @FXML
    private TextField bibNumberOneField;

    @FXML
    private TextField bibNumberTwoField;

    @FXML
    private TextField bibNumberThreeField;

    @FXML
    private TextField bibNumberFourField;

    @FXML
    private TextField bibNumberFiveField;

    @FXML
    private ListView<PersonStart> personStartListView;

    private MainWindow mainWindow;

    public StartPanel(MainWindow mainWindow, ObservableList<PersonStart> personStartList) {
        super(FXML);
        initClock();
        this.mainWindow = mainWindow;
        fields.add(bibNumberOneField);
        fields.add(bibNumberTwoField);
        fields.add(bibNumberThreeField);
        fields.add(bibNumberFourField);
        fields.add(bibNumberFiveField);
        personStartListView.setItems(personStartList);
        personStartListView.setCellFactory(personStartListView -> new PersonStartListViewCell());
    }


    /**
     * Executes a RegisterCommand, based on the input provided in the GUI.
     */
    @FXML
    void executeInput() {
        String command = "start";
        for (TextField field : fields) {
            String input = field.getText();
            if (!input.isEmpty()) {
                command += " b/" + input;
            }
        }
        mainWindow.executeGuiCommand(command);
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
        for (TextField field : fields) {
            field.clear();
        }
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss:SS");
            timer.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(0.001)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonStartListViewCell extends ListCell<PersonStart> {
        @Override
        protected void updateItem(PersonStart person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonStartCard(person, getIndex() + 1).getRoot());
            }
        }
    }
}
