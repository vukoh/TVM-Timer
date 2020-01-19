package seedu.address.model.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

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

    @Override
    public String toString() {
        if (startTime.equals(Instant.MIN)) {
            return "Not Recorded";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(startTime);
    }
}
