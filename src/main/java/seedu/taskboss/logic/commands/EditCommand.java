package seedu.taskboss.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.taskboss.commons.core.EventsCenter;
import seedu.taskboss.commons.core.Messages;
import seedu.taskboss.commons.events.ui.JumpToListRequestEvent;
import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.commons.util.CollectionUtil;
import seedu.taskboss.logic.commands.exceptions.CommandException;
import seedu.taskboss.logic.commands.exceptions.InvalidDatesException;
import seedu.taskboss.model.category.UniqueCategoryList;
import seedu.taskboss.model.category.UniqueCategoryList.DuplicateCategoryException;
import seedu.taskboss.model.task.DateTime;
import seedu.taskboss.model.task.Information;
import seedu.taskboss.model.task.Name;
import seedu.taskboss.model.task.PriorityLevel;
import seedu.taskboss.model.task.ReadOnlyTask;
import seedu.taskboss.model.task.Task;
import seedu.taskboss.model.task.UniqueTaskList;

/**
 * Edits the details of an existing task in TaskBoss.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_WORD_SHORT = "e";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "/" + COMMAND_WORD_SHORT
            + ": Edits the details of the task identified "
            + "by the index number used in the last task listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) [NAME] [p/PRIORITY_LEVEL]"
            + " [sd/START_DATE] [ed/END_DATE]"
            + " [i/INFORMATION ] [c/CATEGORY]...\n"
            + "Example: " + COMMAND_WORD + " 1 p/Yes" + " || " + COMMAND_WORD_SHORT + " 1 p/No";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in TaskBoss.";
    public static final String ERROR_INVALID_DATES = "Your end date is earlier than start date.";

    private final int filteredTaskListIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param filteredTaskListIndex the index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(int filteredTaskListIndex, EditTaskDescriptor editTaskDescriptor) {
        assert filteredTaskListIndex > 0;
        assert editTaskDescriptor != null;

        // converts filteredTaskListIndex from one-based to zero-based.
        this.filteredTaskListIndex = filteredTaskListIndex - 1;

        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute() throws CommandException, InvalidDatesException, IllegalValueException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndex >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        ReadOnlyTask taskToEdit = lastShownList.get(filteredTaskListIndex);
        try {
            Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
            model.updateTask(filteredTaskListIndex, editedTask);
            lastShownList = model.getFilteredTaskList();
            int targetIndex = lastShownList.indexOf(editedTask);
            EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
        } catch (InvalidDatesException ide) {
            throw new CommandException(ERROR_INVALID_DATES);
        } catch (UniqueTaskList.DuplicateTaskException dpe) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.updateFilteredListToShowAll();
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskToEdit));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     * @throws InvalidDatesException
     */
    private static Task createEditedTask(ReadOnlyTask taskToEdit,
                                             EditTaskDescriptor editTaskDescriptor)
                                                     throws InvalidDatesException, DuplicateCategoryException,
                                                     IllegalValueException, InvalidDatesException,
                                                     IllegalValueException {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElseGet(taskToEdit::getName);
        PriorityLevel updatedPriorityLevel = editTaskDescriptor.getPriorityLevel()
                .orElseGet(taskToEdit::getPriorityLevel);
        DateTime updatedStartDateTime = editTaskDescriptor.getStartDateTime()
                .orElseGet(taskToEdit::getStartDateTime);
        DateTime updatedEndDateTime = editTaskDescriptor.getEndDateTime()
                .orElseGet(taskToEdit::getEndDateTime);
        Information updatedInformation = editTaskDescriptor.getInformation()
                .orElseGet(taskToEdit::getInformation);
        UniqueCategoryList updatedCategories = editTaskDescriptor.getCategories()
                .orElseGet(taskToEdit::getCategories);

        if (updatedStartDateTime.getDate() != null &&
                updatedEndDateTime.getDate() != null &&
                updatedStartDateTime.getDate().after(updatedEndDateTime.getDate())) {
            throw new InvalidDatesException(ERROR_INVALID_DATES);
        }

        return new Task(updatedName, updatedPriorityLevel, updatedStartDateTime, updatedEndDateTime,
                updatedInformation, updatedCategories);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Optional<Name> name = Optional.empty();

        private Optional<PriorityLevel> priorityLevel = Optional.empty();
        private Optional<Information> information = Optional.empty();
        private Optional<DateTime> startDateTime = Optional.empty();
        private Optional<DateTime> endDateTime = Optional.empty();
        private Optional<UniqueCategoryList> categories = Optional.empty();

        public EditTaskDescriptor() {}

        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            this.name = toCopy.getName();
            this.priorityLevel = toCopy.getPriorityLevel();
            this.startDateTime = toCopy.getStartDateTime();
            this.endDateTime = toCopy.getEndDateTime();
            this.information = toCopy.getInformation();
            this.categories = toCopy.getCategories();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyPresent(this.name, this.priorityLevel, this.startDateTime,
                    this.endDateTime, this.information, this.categories);
        }

        public void setName(Optional<Name> name) {
            assert name != null;
            this.name = name;
        }

        public Optional<Name> getName() {
            return name;
        }

        public void setPriorityLevel(Optional<PriorityLevel> priorityLevel) {
            assert priorityLevel != null;
            this.priorityLevel = priorityLevel;
        }

        public Optional<PriorityLevel> getPriorityLevel() {
            return priorityLevel;
        }

        public void setStartDateTime(Optional<DateTime> startDateTime) throws IllegalValueException {
            assert startDateTime != null;

            if (startDateTime.isPresent()) {
                String formattedString = startDateTime.get().formatDateTime();
                DateTime formattedDateTime = new DateTime(formattedString);
                this.startDateTime = Optional.of(formattedDateTime);
            } else {
                this.startDateTime = startDateTime;
            }
        }

        public Optional<DateTime> getStartDateTime() {
            return startDateTime;
        }

        public void setEndDateTime(Optional<DateTime> endDateTime) throws IllegalValueException {
            assert endDateTime != null;

            if (endDateTime.isPresent()) {
                String formattedString = endDateTime.get().formatDateTime();
                DateTime formattedDateTime = new DateTime(formattedString);
                this.endDateTime = Optional.of(formattedDateTime);
            } else {
                this.endDateTime = endDateTime;
            }
        }

        public Optional<DateTime> getEndDateTime() {
            return endDateTime;
        }

        public void setInformation(Optional<Information> information) {
            assert information != null;
            this.information = information;
        }

        public Optional<Information> getInformation() {
            return information;
        }

        public void setCategories(Optional<UniqueCategoryList> categories) {
            assert categories != null;
            this.categories = categories;
        }

        public Optional<UniqueCategoryList> getCategories() {
            return categories;
        }

    }
}
