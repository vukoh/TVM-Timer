package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.time.EndTime;

/**
 * Unmodifiable view of EndTimes
 */
public interface ReadOnlyEndTimes {

    /**
     * Returns an unmodifiable view of the endTimes list.
     * This list will not contain any duplicate endTimes.
     */
    ObservableList<EndTime> getEndTimeList();

}
