package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate PersonRegisters (PersonRegisters are considered duplicates if
 * they have the same identity).
 */
public class DuplicatePersonRegisterException extends RuntimeException {
    public DuplicatePersonRegisterException() {
        super("Operation would result in duplicate person registers!");
    }
}
