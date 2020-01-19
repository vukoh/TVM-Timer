package seedu.address.model.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Represents the end time of in the application (To be recorded in UniqueEndTimeList and later assigned to relevant
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
        this.endTime = Instant.now();
    }

    public EndTime(Instant endTime) {
        this.endTime = endTime;
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

    @Override
    public String toString() {
        if (endTime.equals(Instant.MAX)) {
            return "Not Recorded";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(endTime);
    }
}
