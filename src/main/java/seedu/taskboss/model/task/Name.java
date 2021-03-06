package seedu.taskboss.model.task;

import seedu.taskboss.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's name in TaskBoss.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final int MAX_LENGTH = 45;
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "The length of a task name should not be longer than " + MAX_LENGTH;
    public static final String EMPTY_STRING = "";


    /*
     * The first character of the information must not be empty
     */

    public final String fullName;

    //@author A0144904H
    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        assert name != null;
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid task name.
     */
    //@@author A0147990R
    public static boolean isValidName(String test) {
        return (!test.equals(EMPTY_STRING)) && test.length() <= MAX_LENGTH;
    }

    //@@author
    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
