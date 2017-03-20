package seedu.taskboss.model.category;

import seedu.taskboss.commons.exceptions.IllegalValueException;

public class DefaultCategory extends Category {

    private static DefaultCategory allTasks;

    /**
     * Validates given default category name.
     *
     * @throws IllegalValueException if the given default category name string is invalid.
     */
    private DefaultCategory(String name) throws IllegalValueException {
        super(name);
    }

    /**
     * Returns true if a category is a default category
     */
    @Override
    public boolean isDefault() {
        return true;
    }

    public static DefaultCategory getDefaultCategory() throws IllegalValueException {
        if (allTasks == null) {
            allTasks = new DefaultCategory("All Tasks");
        }
        return allTasks;
    }

    @Override
    public String toString() {
        return allTasks.toString();
    }

}
