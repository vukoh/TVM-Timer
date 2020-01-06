package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate PersonStarts (PersonStarts are considered duplicates if
 * they have the same identity).
 */
public class DuplicatePersonStartException extends RuntimeException {
    public DuplicatePersonStartException() {
        super("Operation would result in duplicate person starts!");
    }
}
