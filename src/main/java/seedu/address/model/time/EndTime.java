package seedu.address.model.time;

import java.time.Instant;

/**
 * Represents the end time of in the application (To be recorded in EndTimeList and later assigned to relevant
 * PersonResults).
 */
public class EndTime {

    //Instant object type used to be able to compare from a fixed standard starting time
    private Instant endTime;

    /**
     * Constructs a {@code EndTime}.
     * Initializes endTime variable to local system time.
     */
    public EndTime() {
        endTime = Instant.now();
    }

    public Instant getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EndTime)) {
            return false;
        }

        EndTime otherEndTime = (EndTime) other;
        return otherEndTime.getEndTime().equals(getEndTime());
    }
}