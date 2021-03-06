package seedu.taskboss.logic.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;

import seedu.taskboss.commons.core.EventsCenter;
import seedu.taskboss.commons.core.LogsCenter;
import seedu.taskboss.commons.core.Messages;
import seedu.taskboss.commons.core.UnmodifiableObservableList;
import seedu.taskboss.commons.events.ui.JumpToListRequestEvent;
import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.commons.util.StringUtil;
import seedu.taskboss.logic.commands.exceptions.CommandException;
import seedu.taskboss.model.category.Category;
import seedu.taskboss.model.task.ReadOnlyTask;

//@@author A0144904H
public class TerminateCommand extends Command {

    private static final Logger logger = LogsCenter.getLogger(TerminateCommand.class);

    private static final int INDEX_ZERO = 0;
    public static final String COMMAND_WORD = "terminate";
    public static final String COMMAND_WORD_SHORT = "t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "/" + COMMAND_WORD_SHORT
            + ": Marks the recurring tasks identified by the index"
            + " numbers provided that are used in the last listing as done. "
            + "Parameters: LIST OF INDEXES (must be a positive integers)\n"
            + "Example: " + COMMAND_WORD
            + " 1 2 3" +  " || " + COMMAND_WORD_SHORT + " 1";

    public static final String MESSAGE_MARK_RECURRING_TASK_DONE_SUCCESS = "Recurring task(s) marked done: \n%1$s";
    public static final String DONE = "Done";
    public static final String ERROR_TASK_NOT_RECURRING = "All tasks indicated should be recurring";
    public static final String ERROR_TERMINATED_TASK = "The task was terminated previously";

    public final ArrayList<Integer> filteredTaskListIndices;
    public final ArrayList<ReadOnlyTask> recurringTasksToMarkDone;

    public TerminateCommand(Set<Integer> targetIndex) {
        filteredTaskListIndices = new ArrayList<Integer>(targetIndex);
        Collections.sort(filteredTaskListIndices);
        Collections.reverse(filteredTaskListIndices);
        recurringTasksToMarkDone = new ArrayList<ReadOnlyTask>();
    }

    @Override
    public CommandResult execute() throws CommandException, IllegalValueException {
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        if (filteredTaskListIndices.get(filteredTaskListIndices.size() - 1) < 1
                || filteredTaskListIndices.get(INDEX_ZERO) > lastShownList.size()) {
            logger.info("User input index(es) out of bound.");
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        for (int index : filteredTaskListIndices) {
            ReadOnlyTask recurringTaskToMarkDone = lastShownList.get(index - 1);
            if (recurringTaskToMarkDone.getCategories().contains(Category.done)) {
                logger.info("User attempted to terminate a task that was marked previosuly. "
                        + "Throwing commandException.");
                throw new CommandException(ERROR_TERMINATED_TASK);
            }
            recurringTasksToMarkDone.add(recurringTaskToMarkDone);
        }

        logger.info("Attempting to terminate task(s).");
        model.terminate(filteredTaskListIndices, recurringTasksToMarkDone);

        scrollToTask(recurringTasksToMarkDone);
        return new CommandResult(String.format(MESSAGE_MARK_RECURRING_TASK_DONE_SUCCESS,
                StringUtil.getDesiredArrayListFormat(recurringTasksToMarkDone)));
    }

    /**
     * Scrolls to the position of the task with the lowest index
     * in the mark done list
     */
    private void scrollToTask(ArrayList<ReadOnlyTask> recurringTasksToMarkDone) {
        ReadOnlyTask recurringTaskToMarkDone = recurringTasksToMarkDone.get(INDEX_ZERO);
        UnmodifiableObservableList<ReadOnlyTask> latestShownList = model.getFilteredTaskList();
        int targetIndex = latestShownList.indexOf(recurringTaskToMarkDone);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(targetIndex));
    }
}
