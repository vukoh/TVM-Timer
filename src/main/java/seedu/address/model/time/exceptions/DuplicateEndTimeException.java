package seedu.address.model.time.exceptions;

/**
 * Signals that the operation will result in duplicate EndTimes (EndTimes are considered duplicates if
 * they have the same identity).
 */
public class DuplicateEndTimeException extends RuntimeException {
    public DuplicateEndTimeException() {
        super("Operation would result in duplicate end times!");
    }
}
