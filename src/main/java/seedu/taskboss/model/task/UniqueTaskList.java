package seedu.taskboss.model.task;

import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taskboss.commons.core.LogsCenter;
import seedu.taskboss.commons.core.UnmodifiableObservableList;
import seedu.taskboss.commons.exceptions.DuplicateDataException;
import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.commons.util.CollectionUtil;
import seedu.taskboss.logic.commands.RenameCategoryCommand;
import seedu.taskboss.logic.commands.SortCommand;
import seedu.taskboss.logic.commands.exceptions.CommandException;
import seedu.taskboss.model.category.Category;
import seedu.taskboss.model.category.UniqueCategoryList;
import seedu.taskboss.model.category.UniqueCategoryList.DuplicateCategoryException;

/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    private final Logger logger = LogsCenter.getLogger(UniqueTaskList.class);

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    //@@author A0143157J
    public enum SortBy {
        START_DATE_TIME, END_DATE_TIME, PRIORITY_LEVEL
    }

    /**
     * Sorts tasks based on the specified sort type.
     * Start and end dates are sorted in ascending order,
     * whereas priority level is sorted in descending order
     * (i.e tasks with high priority will be listed on top)
     * @throws IllegalValueException
     */
    public void sort(SortBy sortType) throws IllegalValueException {
        Comparator<ReadOnlyTask> taskCmp = null;
        switch(sortType) {
        case START_DATE_TIME:
            taskCmp =  new Comparator<ReadOnlyTask>() {
                @Override
                public int compare(ReadOnlyTask task1, ReadOnlyTask task2) {
                    Date startDate1 = task1.getStartDateTime().getDate();
                    Date startDate2 = task2.getStartDateTime().getDate();
                    return compareDateTime(startDate1, startDate2);
                }
            };
            break;

        case END_DATE_TIME:
            taskCmp = new Comparator<ReadOnlyTask> () {
                @Override
                public int compare(ReadOnlyTask task1, ReadOnlyTask task2) {
                    Date endDate1 = task1.getEndDateTime().getDate();
                    Date endDate2 = task2.getEndDateTime().getDate();
                    return compareDateTime(endDate1, endDate2);
                }
            };
            break;

        case PRIORITY_LEVEL:
            taskCmp = new Comparator<ReadOnlyTask> () {
                @Override
                public int compare(ReadOnlyTask task1, ReadOnlyTask task2) {
                    String priorityLevel1 = task1.getPriorityLevel().toString();
                    String priorityLevel2 = task2.getPriorityLevel().toString();
                    return comparePriorityLevel(priorityLevel1, priorityLevel2);
                }

            };
            break;

        default:
            throw new IllegalValueException(SortCommand.MESSAGE_USAGE);
        }

        FXCollections.sort(internalList, taskCmp);
    }

    /**
     * Compares {@code date1} with {@code date2}.
     * Earlier date will take precedence.
     * A null value is seen as having a lower precedence than a non-null date.
     */
    private int compareDateTime(Date date1, Date date2) {
        if (date1 == null &&
                date2 == null) {
            return 0;
        } else if (date1 == null) {
            return 1;
        } else if (date2 == null) {
            return -1;
        } else {
            return date1.compareTo(date2);
        }
    }

    /**
     * Compares {@code priorityLevel1} with {@code priorityLevel2}.
     * High priority will take precedence.
     */
    private int comparePriorityLevel(String priorityLevel1, String priorityLevel2) {
        if (priorityLevel1.equals(priorityLevel2)) {
            return 0;
        } else if (priorityLevel1.equals(PriorityLevel.PRIORITY_HIGH_VALUE)) {
            return -1;
        } else if (priorityLevel2.equals(PriorityLevel.PRIORITY_HIGH_VALUE)) {
            return 1;
        } else {
            return 0;
        }
    }

    //@@author
    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd) && !toAdd.getCategories().contains(Category.done)) {
            throw new DuplicateTaskException();
        }
        internalList.add(toAdd);
    }

    /**
     * Updates the task in the list at position {@code index} with {@code editedTask}.
     *
     * @throws DuplicateTaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code index} < 0 or >= the size of the list.
     */
    public void updateTask(int index, ReadOnlyTask editedTask) throws DuplicateTaskException {
        assert editedTask != null;

        Task taskToUpdate = internalList.get(index);
        if (!taskToUpdate.equals(editedTask) && internalList.contains(editedTask) &&
                !editedTask.getCategories().contains(Category.done)) {
            throw new DuplicateTaskException();
        }

        taskToUpdate.resetData(editedTask);
        // TODO: The code below is just a workaround to notify observers of the updated task.
        // The right way is to implement observable properties in the Task class.
        // Then, TaskCard should then bind its text labels to those observable properties.
        internalList.set(index, taskToUpdate);
    }

    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    //@@author A0143157J
    /**
     * Renames a certain category for all tasks in this category.
     * @throws IllegalValueException
     * @throws CommandException
     */
    public void renameCategory(Category oldCategory, Category newCategory) throws IllegalValueException,
                                                                                CommandException {
        assert oldCategory != null;

        boolean isOldCategoryFound = false;

        for (Task task : this) {
            UniqueCategoryList targetCategoryList = task.getCategories();
            UniqueCategoryList newCategoryList = new UniqueCategoryList();
            try {
                isOldCategoryFound = initNewCategoryList(oldCategory, newCategory, isOldCategoryFound,
                        targetCategoryList, newCategoryList);
            } catch (DuplicateCategoryException dce) {
                logger.info("User attempted to rename a category to a name that already exists in TaskBoss."
                        + " Throwing DuplicateCategoryException.");
                throw new DuplicateCategoryException();
            }
            task.setCategories(newCategoryList);
        }
        errorDoesNotExistDetect(oldCategory, isOldCategoryFound);
    }

    /**
     * Initialises {@code newCategoryList} and returns true if {@code oldCategory}
     * is found in {@code targetCategoryList}
     * @throws IllegalValueException, DuplicateCategoryException
     */
    private boolean initNewCategoryList(Category oldCategory, Category newCategory, boolean isOldCategoryFound,
            UniqueCategoryList targetCategoryList, UniqueCategoryList newCategoryList)
            throws IllegalValueException, DuplicateCategoryException {
        boolean isFound = isOldCategoryFound;
        for (Category category : targetCategoryList) {
            if (category.equals(oldCategory)) {
                isFound = true;
                newCategoryList.add(newCategory);
            } else {
                newCategoryList.add(category);
            }
        }
        return isFound;
    }

    //@@author A0144904H
    /**
     * detects the category does not exist error
     * @param oldCategory
     * @param isFound
     * @throws CommandException
     */
    private void errorDoesNotExistDetect(Category oldCategory, boolean isFound) throws CommandException {
        if (!isFound) {
            throw new CommandException(oldCategory.toString()
                    + " " + RenameCategoryCommand.MESSAGE_DOES_NOT_EXIST_CATEGORY);
        }
    }

    //@@author
    public void setTasks(UniqueTaskList replacement) {
        this.internalList.setAll(replacement.internalList);
    }

    public void setTasks(List<? extends ReadOnlyTask> tasks) throws DuplicateTaskException {
        final UniqueTaskList replacement = new UniqueTaskList();
        for (final ReadOnlyTask task : tasks) {
            replacement.add(new Task(task));
        }
        setTasks(replacement);
    }

    public UnmodifiableObservableList<Task> asObservableList() {
        return new UnmodifiableObservableList<>(internalList);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                        && this.internalList.equals(
                                ((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {}

}
