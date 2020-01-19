package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.PersonRegister;
import seedu.address.model.person.PersonResult;

/**
 * Panel containing the list of persons.
 */
public class PersonResultPanel extends UiPart<Region> {
    private static final String FXML = "PersonResultListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<PersonResult> personResultListView;

    public PersonResultPanel(ObservableList<PersonResult> personList) {
        super(FXML);
        personResultListView.setItems(personList);
        personResultListView.setCellFactory(listView -> new PersonResultListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonResultListViewCell extends ListCell<PersonResult> {
        @Override
        protected void updateItem(PersonResult person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonResultCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
