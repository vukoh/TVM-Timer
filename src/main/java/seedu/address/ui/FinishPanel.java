package seedu.address.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import seedu.address.model.time.EndTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinishPanel extends UiPart<Region> {

    private static final String FXML = "FinishPanel.fxml";

    @FXML
    private Label timer;

    @FXML
    private TextField runnersCompleted;

    @FXML
    private ListView<EndTime> endTimeListView;

    private MainWindow mainWindow;

    public FinishPanel(MainWindow mainWindow, ObservableList<EndTime> endTimeList) {
        super(FXML);
        initClock();
        this.mainWindow = mainWindow;
        endTimeListView.setItems(endTimeList);
        endTimeListView.setCellFactory(personStartListView -> new EndTimeListViewCell());
    }


    /**
     * Executes a RegisterCommand, based on the input provided in the GUI.
     */
    @FXML
    void executeInput(int noRacers) {
        String command = "finish";
        for(int i = 0; i < noRacers; i++) {
            mainWindow.executeGuiCommand(command);
        }
    }

    @FXML
    void handleFinishAction() {
        executeInput(1);
    }

    @FXML
    void handleConfirmAction() {
        int noRacers = Integer.valueOf(runnersCompleted.getText());
        executeInput(noRacers);
    }

    @FXML
    void handleCancelAction() {
        runnersCompleted.clear();
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
    class EndTimeListViewCell extends ListCell<EndTime> {
        @Override
        protected void updateItem(EndTime endTime, boolean empty) {
            super.updateItem(endTime, empty);

            if (empty || endTime == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new EndTimeCard(endTime, getIndex() + 1).getRoot());
            }
        }
    }
}
