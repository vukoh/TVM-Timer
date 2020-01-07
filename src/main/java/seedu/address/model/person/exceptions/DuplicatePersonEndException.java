package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate PersonEnds (PersonEnds are considered duplicates if
 * they have the same identity).
 */
public class DuplicatePersonEndException extends RuntimeException {
    public DuplicatePersonEndException() {
        super("Operation would result in duplicate person ends!");
    }
}
