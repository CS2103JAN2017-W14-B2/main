package seedu.taskboss.model.category;


import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.model.ReadOnlyTaskBoss;
import seedu.taskboss.model.task.ReadOnlyTask;
import seedu.taskboss.model.task.Task;
import seedu.taskboss.model.task.UniqueTaskList;
import seedu.taskboss.model.task.UniqueTaskList.DuplicateTaskException;

/**
 * Represents a Category in TaskBoss.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCategoryName(String)}
 */
public class Category {

    public static final String MESSAGE_CATEGORY_CONSTRAINTS = "Categories names should be alphanumeric";
    public static final String CATEGORY_VALIDATION_REGEX = "\\p{Alnum}+";

    public final String categoryName;
    private final UniqueTaskList taskList;

    {
        taskList = new UniqueTaskList();
    }

    /**
     * Validates given category name.
     *
     * @throws IllegalValueException if the given category name string is invalid.
     */
    public Category(String name) throws IllegalValueException {
        assert name != null;
        String trimmedName = name.trim();
        if (!isValidCategoryName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_CATEGORY_CONSTRAINTS);
        }
        this.categoryName = trimmedName;
    }

    public void setTasks(List<? extends ReadOnlyTask> tasks)
            throws UniqueTaskList.DuplicateTaskException {
        this.taskList.setTasks(tasks);
    }

    public void resetData(ReadOnlyTaskBoss newData) {
        assert newData != null;
        try {
            setTasks(newData.getTaskList());
        } catch (UniqueTaskList.DuplicateTaskException e) {
            assert false : "A category should not have duplicate tasks";
        }
    }
 
    /**
     * Adds a task to the category.
     *
     * @throws UniqueTaskList.DuplicateTaskException if an equivalent task already exists.
     */
    public void addTask(Task task) throws UniqueTaskList.DuplicateTaskException {
        taskList.add(task);
    }

    /**
     * Updates the task in the list at position {@code index} with {@code editedReadOnlyTask}.
     *
     * @throws DuplicateTaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedReadOnlyTask)
            throws UniqueTaskList.DuplicateTaskException {
        assert editedReadOnlyTask != null;

        Task editedTask = new Task(editedReadOnlyTask);
        taskList.updateTask(index, editedTask);
    }

    public boolean removeTask(ReadOnlyTask key) throws UniqueTaskList.TaskNotFoundException {
        if (taskList.remove(key)) {
            return true;
        } else {
            throw new UniqueTaskList.TaskNotFoundException();
        }
    }

    /**
     * Returns true if a given string is a valid category name.
     */
    public static boolean isValidCategoryName(String test) {
        return test.matches(CATEGORY_VALIDATION_REGEX);
    }

    public UniqueTaskList getTasks() {
        assert taskList != null;
        return taskList;
    }
 
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Category // instanceof handles nulls
                && this.categoryName.equals(((Category) other).categoryName)); // state check
    }

    @Override
    public int hashCode() {
        return categoryName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + categoryName + ']';
    }

    /**
     * Returns true if a category is a default category
     */
    public boolean isDefault() {
        return false;
    }

}
