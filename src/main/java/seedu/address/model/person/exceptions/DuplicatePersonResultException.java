package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate PersonResults (PersonResults are considered duplicates if
 * they have the same identity).
 */
public class DuplicatePersonResultException extends RuntimeException {
    public DuplicatePersonResultException() {
        super("Operation would result in duplicate person results!");
    }
}
