package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.taskboss.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.taskboss.commons.core.Messages;
import seedu.taskboss.logic.commands.AddCommand;
import seedu.taskboss.logic.commands.MarkDoneCommand;
import seedu.taskboss.model.task.Recurrence.Frequency;
import seedu.taskboss.testutil.TaskBuilder;
import seedu.taskboss.testutil.TestTask;

//@@author A0144904H
public class MarkDoneCommandTest extends TaskBossGuiTest {

    // The list of tasks in the task list panel is expected to match this list.
    // This list is updated with every successful call to assertEditSuccess().

    TestTask[] expectedTasksList = td.getTypicalTasks();

    //---------------- Tests for validity of input taskBoss index --------------------------------------

    /*
     * EP: valid task index,
     * should add category "Done" to the task's current category list.
     * - test for long and short command formats
     * - test multiple and single mark done
     */

    //long command format
    //single mark done
    @Test
    public void markDone_validIndexLongCommandFormat_success() throws Exception {
        int taskBossIndex = 1;

        TestTask markedDoneTask = new TaskBuilder().withName("Clean house").withPriorityLevel("Yes")
                .withStartDateTime("Feb 19, 2017 11pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withInformation("wall street").withRecurrence(Frequency.NONE)
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedAllTasks = new TestTask[] {td.taskE, td.taskA, td.taskD, td.taskB, td.taskG, td.taskF};
        TestTask[] expectedDoneTasks = new TestTask[] {markedDoneTask};
        assertMarkDoneSuccess(false, taskBossIndex, taskBossIndex, markedDoneTask, expectedAllTasks, expectedDoneTasks);
    }

    //multiple mark done
    @Test
    public void markDone_multipleValidIndexesLongCommandFormat_success() throws Exception {
        commandBox.runCommand("mark 4 5");

        expectedTasksList[4] = new TaskBuilder().withName("Birthday party")
                .withInformation("311, Clementi Ave 2, #02-25")
                .withPriorityLevel("No")
                .withRecurrence(Frequency.NONE)
                .withStartDateTime("Feb 23, 2017 10pm")
                .withEndDateTime("Jun 28, 2017 5pm")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS,
                        "Friends", "Owesmoney").build();

        expectedTasksList[3] = new TaskBuilder().withName("Debug code").withPriorityLevel("Yes")
                .withStartDateTime("Feb 20, 2017 11.30pm")
                .withEndDateTime("Apr 28, 2017 3pm")
                .withRecurrence(Frequency.NONE)
                .withInformation("10th street")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedDoneTasks = new TestTask[] {expectedTasksList[4], expectedTasksList[3]};
        TestTask[] expectedAllTasks = {td.taskC, td.taskE, td.taskA, td.taskG, td.taskF};


        assertTrue(taskListPanel.isListMatching(expectedAllTasks));

        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                getDesiredFormat(expectedDoneTasks)));
    }

    //short command format
    //single mark done
    @Test
    public void markDone_validIndexShortCommandFormat_success() throws Exception {
        int taskBossIndex = 1;

        TestTask markedDoneTask = new TaskBuilder().withName("Clean house").withPriorityLevel("Yes")
                .withStartDateTime("Feb 19, 2017 11pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withInformation("wall street").withRecurrence(Frequency.NONE)
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedAllTasks = new TestTask[] {td.taskE, td.taskA, td.taskD, td.taskB, td.taskG, td.taskF};
        TestTask[] expectedDoneTasks = new TestTask[] {markedDoneTask};
        assertMarkDoneSuccess(true, taskBossIndex, taskBossIndex, markedDoneTask, expectedAllTasks, expectedDoneTasks);
    }

    //multiple mark done
    @Test
    public void markDone_multipleValidIndexesShortCommandFormat_success() throws Exception {
        commandBox.runCommand("m 4 5");

        expectedTasksList[4] = new TaskBuilder().withName("Birthday party")
                .withInformation("311, Clementi Ave 2, #02-25")
                .withPriorityLevel("No")
                .withRecurrence(Frequency.NONE)
                .withStartDateTime("Feb 23, 2017 10pm")
                .withEndDateTime("Jun 28, 2017 5pm")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS,
                        "Friends", "Owesmoney").build();

        expectedTasksList[3] = new TaskBuilder().withName("Debug code").withPriorityLevel("Yes")
                .withStartDateTime("Feb 20, 2017 11.30pm")
                .withEndDateTime("Apr 28, 2017 3pm")
                .withRecurrence(Frequency.NONE)
                .withInformation("10th street")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedDoneTasks = new TestTask[] {expectedTasksList[4], expectedTasksList[3]};
        TestTask[] expectedAllTasks = {td.taskC, td.taskE, td.taskA, td.taskG, td.taskF};

        assertTrue(taskListPanel.isListMatching(expectedAllTasks));

        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                getDesiredFormat(expectedDoneTasks)));
    }


    /*
     * EP: missing task index,
     * should show error message: invalid command format
     * and display a message demonstrating the correct way to write the command
     */
    @Test
    public void markDone_missingTaskIndex_failure() {
        //long command format
        commandBox.runCommand("mark ");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        //short command format
        commandBox.runCommand("m ");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));
    }

    /*
     * EP: invalid task index,
     * should show error message: invalid index
     * - test short and long command format
     * - test multiple and single mark done
     */

    //single mark done
    @Test
    public void markDone_invalidTaskIndex_failure() {

        //long command format

        commandBox.runCommand("mark 9");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        commandBox.runCommand("mark -1");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // non-numeric inputs
        commandBox.runCommand("mark ^");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        commandBox.runCommand("mark b");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        //short command format

        commandBox.runCommand("m 10");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        commandBox.runCommand("m 0");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // non-numeric inputs
        commandBox.runCommand("m ^");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        commandBox.runCommand("m b");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));
    }

    //multiple mark done
    @Test
    public void markDone_multipleInvalidIndexes_failure() {
        //long command format
        commandBox.runCommand("mark 1 2 100");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        commandBox.runCommand("mark 0 2 3");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        //user inputs non-numeric index values
        commandBox.runCommand("mark a 2 3");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        commandBox.runCommand("mark ; 2 3");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        //short command format
        commandBox.runCommand("m 1 2 100");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        commandBox.runCommand("m 0 2 3");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        //user inputs non-numeric index values
        commandBox.runCommand("m a 2 3");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));

        commandBox.runCommand("m ; 2 3");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE));
    }

    //---------------- Tests for corner cases --------------------------------------------------------

    /*
     * EP: marking a marked task,
     * should show error message: cannot mark marked tasks
     * - test short and long command format
     * - test multiple and single mark done
     */

    //long command format

    //single mark done
    @Test
    public void markDone_taskMarkedDoneLongCommandFormat_failure() {
        commandBox.runCommand("mark 1");
        commandBox.runCommand("list c/done");
        commandBox.runCommand("mark 1");
        assertResultMessage(MarkDoneCommand.ERROR_MARKED_TASK);
    }

    //multiple mark done
    @Test
    public void markDone_multipleTaskMarkedDoneLongCommandFormat_failure() {
        commandBox.runCommand("mark 1 4");
        commandBox.runCommand("list c/done");
        commandBox.runCommand("mark 1 2");
        assertResultMessage(MarkDoneCommand.ERROR_MARKED_TASK);
    }

    //short command format

    //single mark done
    @Test
    public void markDone_taskMarkedDoneShortCommandFormat_failure() {
        commandBox.runCommand("m 1");
        commandBox.runCommand("list c/done");
        commandBox.runCommand("m 1");
        assertResultMessage(MarkDoneCommand.ERROR_MARKED_TASK);
    }

    //multiple mark done
    @Test
    public void markDone_multipleTaskMarkedDoneShortCommandFormat_failure() {
        commandBox.runCommand("m 1 5");
        commandBox.runCommand("list c/done");
        commandBox.runCommand("m 1 2");
        assertResultMessage(MarkDoneCommand.ERROR_MARKED_TASK);
    }

    /*
     * EP: marking multiple tasks with spaces between indexes,
     * should add category "Done" to all the tasks's current category lists.
     */
    @Test
    public void marDone_SpacesInBetweenIndexes_success() throws Exception {
        commandBox.runCommand("mark 5       4 ");
        expectedTasksList[4] = new TaskBuilder().withName("Birthday party")
                .withInformation("311, Clementi Ave 2, #02-25")
                .withPriorityLevel("No")
                .withRecurrence(Frequency.NONE)
                .withStartDateTime("Feb 23, 2017 10pm")
                .withEndDateTime("Jun 28, 2017 5pm")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS,
                                    "Friends", "Owesmoney").build();

        expectedTasksList[3] = new TaskBuilder().withName("Debug code").withPriorityLevel("Yes")
                .withStartDateTime("Feb 20, 2017 11.30pm")
                .withEndDateTime("Apr 28, 2017 3pm")
                .withRecurrence(Frequency.NONE)
                .withInformation("10th street")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedDoneTasks = new TestTask[] {expectedTasksList[4], expectedTasksList[3]};
        TestTask[] expectedAllTasks = {td.taskC, td.taskE, td.taskA, td.taskG, td.taskF};

        assertTrue(taskListPanel.isListMatching(expectedAllTasks));

        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                getDesiredFormat(expectedDoneTasks)));
    }

    //---------------- Tests mark done after find command -----------------------------------------

    /*
     * EP: marking after finding a task,
     * should add category "Done" to the task's current category list.
     */
    @Test
    public void markDone_findThenMarkDone_success() throws Exception {
        commandBox.runCommand("find Clean house");

        int filteredTaskListIndex = 1;
        int taskBossIndex = 1;

        TestTask taskToMarkDone = expectedTasksList[taskBossIndex - 1];
        TestTask markedDoneTask = new TaskBuilder(taskToMarkDone).withCategories(AddCommand.BUILT_IN_DONE,
                AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedDoneTasks = new TestTask[] {markedDoneTask};

        assertMarkDoneSuccess(false, filteredTaskListIndex, taskBossIndex, markedDoneTask,
                expectedDoneTasks, expectedDoneTasks);
    }

    //---------------- Test for different types of tasks --------------------------------------


    /*
     * EP: marking non-recurring task,
     * should add category "Done" to the task's current category list.
     */
    @Test
    public void markDone_markNonRecurringTask_success() throws Exception {
        int taskBossIndex = 1;

        TestTask markedDoneTask = new TaskBuilder().withName("Clean house").withPriorityLevel("Yes")
                .withStartDateTime("Feb 19, 2017 11pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withInformation("wall street").withRecurrence(Frequency.NONE)
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedAllTasks = new TestTask[] {td.taskE, td.taskA, td.taskD, td.taskB, td.taskG, td.taskF};
        TestTask[] expectedDoneTasks = new TestTask[] {markedDoneTask};
        assertMarkDoneSuccess(false, taskBossIndex, taskBossIndex, markedDoneTask,
                expectedAllTasks, expectedDoneTasks);
    }

    /*
     * EP: marking recurring task,
     * should update task's dates based on recurrence type.
     */
    @Test

    public void markDone_markRecurringTask_success() throws Exception {
        commandBox.runCommand("mark 2");
        TestTask markedDoneTask =  new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Mar 22, 2017 5pm")
                .withEndDateTime("Mar 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories(AddCommand.BUILT_IN_ALL_TASKS).build();
        TestTask[] expectedAllTasks = expectedTasksList;
        expectedAllTasks[1] = markedDoneTask;

        assertTrue(taskListPanel.isListMatching(expectedAllTasks));

        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                getDesiredFormat(new TestTask[] {markedDoneTask})));
    }

    /*
     * EP: marking recurring and non-recurring tasks at the same time,
     * should update the recurring task's dates based on recurrence type.
     * should add category "Done" to the non-recurring task's current category list.
     */
    @Test
    public void markDone_markMixTypesOfTasks_success() throws Exception {

        commandBox.runCommand("mark 2 4");

        // recurring
        TestTask markedDone1 = new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Mar 22, 2017 5pm")
                .withEndDateTime("Mar 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories(AddCommand.BUILT_IN_ALL_TASKS).build();

        //non recurring
        TestTask markedDone2 = new TaskBuilder().withName("Debug code").withPriorityLevel("Yes")
                .withStartDateTime("Feb 20, 2017 11.30pm")
                .withEndDateTime("Apr 28, 2017 3pm")
                .withRecurrence(Frequency.NONE)
                .withInformation("10th street")
                .withCategories(AddCommand.BUILT_IN_DONE, AddCommand.BUILT_IN_ALL_TASKS).build();

        TestTask[] expectedAllTasks = {td.taskC, td.taskE, td.taskA, td.taskB, td.taskG, td.taskF};
        expectedAllTasks[1] = markedDone1;
        TestTask[] markedDone = new TestTask[] {markedDone2, markedDone1};

        assertTrue(taskListPanel.isListMatching(expectedAllTasks));
        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS,
                getDesiredFormat(markedDone)));
    }

    //---------------- End of test cases --------------------------------------

    //@@author A0147990R
    private void assertMarkDoneSuccess(boolean isShort, int filteredTaskListIndex, int taskBossIndex,
            TestTask markedDoneTask, TestTask[] expectedAllTasks, TestTask[] expectedDoneTasks) {

        if (isShort) {
            commandBox.runCommand("m " + filteredTaskListIndex);
        } else {
            commandBox.runCommand("mark " + filteredTaskListIndex);
        }

        // confirm the Alltasks category now does not contain the markedDoneTask
        assertTrue(taskListPanel.isListMatching(expectedAllTasks));
        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS, "1. " +
                markedDoneTask));

        // confirm the Done category now contains the markedDoneTask
        commandBox.runCommand("list c/done");
        assertTrue(taskListPanel.isListMatching(expectedDoneTasks));

    }

    //@@author
    /**
     * Returns a formatted {@code Array} tasksToMarkDone,
     * so that each TestTask in the Array is numbered
     */
    private String getDesiredFormat(TestTask[] markedDoneTask) {
        int indexOne = 1;
        String numberingDot = ". ";
        int i = indexOne;
        StringBuilder builder = new StringBuilder();
        for (TestTask task : markedDoneTask) {
            builder.append(i + numberingDot).append(task.toString());
            i++;
        }
        return builder.toString();
    }
}
