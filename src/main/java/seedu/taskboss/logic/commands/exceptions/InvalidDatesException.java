package seedu.taskboss.logic.commands.exceptions;

//@@author A0143157J
/**
 * Signals an error caused by having a task's end date
 * earlier than its start date
 */
public class InvalidDatesException extends Exception {
    public InvalidDatesException(String message) {
        super(message);
    }
}
