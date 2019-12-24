package seedu.address.model.time;

import java.time.Instant;

/**
 * Represents the start time of a PersonStart object.
 */
public class StartTime {

    //Instant object type used to be able to compare from a fixed standard starting time
    private Instant startTime;

    public Instant getStartTime() {
        return startTime;
    }

    /**
     * Initializes startTime variable to local system time.
     */
    public void start() {
        this.startTime = Instant.now();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StartTime)) {
            return false;
        }

        StartTime otherStartTime = (StartTime) other;
        return otherStartTime.getStartTime().equals(getStartTime());
    }
}