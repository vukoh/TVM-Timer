package seedu.address.model.time;

import java.time.Instant;

/**
 * Represents the start time of a PersonStart object.
 */
public class StartTime {

    //Instant object type used to be able to compare from a fixed standard starting time
    private Instant startTime;

    public StartTime() {
        this.startTime = Instant.now();
    }

    public StartTime(Instant instant) {
        this.startTime = instant;
    }

    public Instant getStartTime() {
        return startTime;
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
