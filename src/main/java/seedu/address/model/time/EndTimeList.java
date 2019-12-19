package seedu.address.model.time;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all end times. To add end times as marathoners come in.
 */
public class EndTimeList {

    private List<EndTime> endTimeList = new ArrayList<>();

    //To use when marathoner comes in
    public void addEndTime() {
        this.endTimeList.add(new EndTime());
    }

    public List<EndTime> getEndTimeList() {
        return this.endTimeList;
    }
}