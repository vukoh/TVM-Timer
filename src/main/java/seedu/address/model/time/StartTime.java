package seedu.address.model.time;

import java.time.Instant;

/**
 * Represents the start time of a PersonStart object.
 */
public class StartTime {

    //Instant object type used to be able to compare from a fixed standard starting time
    private Instant startTime;

    /**
     * Constructs a {@code StartTime}.
     * Initializes startTime variable to local system time.
     */
    public StartTime() {
        startTime = Instant.now();
    }
}