package seedu.address.ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.FunctionMode;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.commandresults.CalculateCommandResult;
import seedu.address.logic.commands.commandresults.FinishCommandResult;
import seedu.address.logic.commands.commandresults.GlobalCommandResult;
import seedu.address.logic.commands.commandresults.RegisterCommandResult;
import seedu.address.logic.commands.commandresults.StartCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.UnknownCommandResultTypeException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private PersonListPanel personListPanel;
    private RegisterPanel registerPanel;
    private StartPanel startPanel;
    private FinishPanel finishPanel;
    private ResultDisplay resultDisplay;
    private PersonResultPanel personResultPanel;
    private HelpWindow helpWindow;

    @FXML
    private Label featureMode;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane versatilePanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        initializePanels();

        versatilePanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Initializes all Panels.
     */
    void initializePanels() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonRegisterList());
        personResultPanel = new PersonResultPanel(logic.getFilteredPersonResultList());
        registerPanel = new RegisterPanel(this);
        startPanel = new StartPanel(this, logic.getFilteredPersonStartList());
        finishPanel = new FinishPanel(this, logic.getFilteredEndTimeList());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException,
            UnknownCommandResultTypeException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());

            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isGlobalCommandResult()) {
                executeGlobalCommandHelper((GlobalCommandResult) commandResult);
            } else if (commandResult.isRegisterCommandResult()) {
                executeRegisterCommandHelper((RegisterCommandResult) commandResult);
            } else if (commandResult.isStartCommandResult()) {
                executeStartCommandHelper((StartCommandResult) commandResult);
            } else if (commandResult.isFinishCommandResult()) {
                executeFinishCommandHelper((FinishCommandResult) commandResult);
            } else if (commandResult.isCalculateCommandResult()) {
                executeCalculateCommandHelper((CalculateCommandResult) commandResult);
            } else {
                throw new UnknownCommandResultTypeException("Invalid CommandResult type!");
            }
            return commandResult;
        } catch (CommandException | ParseException | UnknownCommandResultTypeException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    private void executeGlobalCommandHelper(GlobalCommandResult globalCommandResult) {
        if (globalCommandResult.isShowHelp()) {
            handleHelp();
        }

        if (globalCommandResult.isExit()) {
            handleExit();
        }

        if (globalCommandResult.isToggle()) {
            toggleModeTo(globalCommandResult.getTargetMode().get());
        }
    }

    /**
     * Switches the list of available commands based on the function that the user wants to use.
     * @param targetMode Function mode that user wants to switch to
     */
    private void toggleModeTo (FunctionMode targetMode) {
        //To change to reflect GUI changes, if any
        versatilePanelPlaceholder.getChildren().clear();
        switch (targetMode) {
            case REGISTER:
                versatilePanelPlaceholder.getChildren().add(registerPanel.getRoot());
                featureMode.setText("Register Mode");
                System.out.println("Switched to 'Register' mode");
                break;
            case START:
                featureMode.setText("Start Mode");
                System.out.println("Switched to 'Start' mode");
                versatilePanelPlaceholder.getChildren().add(startPanel.getRoot());
                break;
            case FINISH:
                featureMode.setText("Finish Mode");
                System.out.println("Switched to 'Finish' mode");
                versatilePanelPlaceholder.getChildren().add(finishPanel.getRoot());
                break;
            case CALCULATE:
                featureMode.setText("Calculate Mode");
                System.out.println("Switched to 'Calculate' mode");
                versatilePanelPlaceholder.getChildren().add(personResultPanel.getRoot());
                break;
            default:
                featureMode.setText("Home");
                versatilePanelPlaceholder.getChildren().add(personListPanel.getRoot());
        }
    }

    /**
     * Switch to register mode.
     */
    @FXML
    private void switchRegister() {
        executeGuiCommand("switch register");
        resultDisplay.setFeedbackToUser("");
    }

    /**
     * Switch to start mode.
     */
    @FXML
    private void switchStart() {
        executeGuiCommand("switch start");
        resultDisplay.setFeedbackToUser("");
    }

    /**
     * Switch to finish mode.
     */
    @FXML
    private void switchFinish() {
        executeGuiCommand("switch finish");
        resultDisplay.setFeedbackToUser("");
    }

    /**
     * Switch to calculate mode.
     */
    @FXML
    private void switchCalculate() {
        executeGuiCommand("switch calculate");
        resultDisplay.setFeedbackToUser("");
    }

    /**
     * Switch to calculate mode.
     */
    @FXML
    private void switchHome() {
        toggleModeTo(FunctionMode.UNDEFINED);
        resultDisplay.setFeedbackToUser("");
    }

    private void executeRegisterCommandHelper(RegisterCommandResult registerCommandResult) {
        //To fill
    }

    private void executeStartCommandHelper(StartCommandResult startCommandResult) {
        //To fill
    }

    private void executeFinishCommandHelper(FinishCommandResult finishCommandResult) {
        //To fill
    }

    private void executeCalculateCommandHelper(CalculateCommandResult calculateCommandResult) {
        //To fill
    }

    /**
     * Executes the command from GUI and returns the {@code CommandResult}.
     *
     * @see Logic#execute(String)
     */
    public CommandResult executeGuiCommand(String commandText) {
        try {
            return executeCommand(commandText);
        } catch (CommandException | ParseException | UnknownCommandResultTypeException e) {
            return null;
        }
    }
}
