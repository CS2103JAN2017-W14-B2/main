package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.taskboss.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.taskboss.commons.core.Messages;
import seedu.taskboss.logic.commands.EndCommand;
import seedu.taskboss.logic.commands.MarkDoneCommand;
import seedu.taskboss.model.task.Recurrence.Frequency;
import seedu.taskboss.testutil.TaskBuilder;
import seedu.taskboss.testutil.TestTask;

//@@author A0144904H
public class EndCommandTest extends TaskBossGuiTest {

    // The list of tasks in the task list panel is expected to match this list.
    // This list is updated with every successful call to assertEditSuccess().
    TestTask[] expectedTasksList = td.getTypicalTasks();

    //---------------- Tests for validity of input taskBoss index --------------------------------------

    /*
     * EP: valid task index, should remove all
     * task's current categories and add category "Done" in their place.
     *
     * Should return true.
     */

    @Test
    public void endTask_success() throws Exception {
        int taskBossIndex = 2;

        TestTask endedTask = new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Feb 22, 2017 5pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories("Done").build();

        assertEndSuccess(false, taskBossIndex, taskBossIndex, endedTask);
    }

    /*
     * Invalid index equivalence partitions for : 1) missing index
     * 2) index invalid for not existing in task list.
     *
     * The two test cases below test one invalid index input type at a time
     * for each of the two invalid possible cases.
     */

    /*
     * EP: invalid task index where index was not entered and is therefore missing,
     * should not any of the task's
     * current categories and will not add category "Done".
     *
     * Should show error message that command entered was in the wrong format
     * and an index should be entered.
     *
     * Should return false.
     */

    @Test
    public void endTask_missingTaskIndex_failure() {
        commandBox.runCommand("terminate ");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EndCommand.MESSAGE_USAGE));
    }

    /*
     * EP: invalid task index where the index entered does
     * not exist in current task list, should not remove any of the task's
     * current categories and will not add category "Done".
     *
     * Should show error message that index entered is invalid.
     *
     * Should return false.
     */

    @Test
    public void endTask_invalidTaskIndex_failure() {
        commandBox.runCommand("terminate 9");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    //---------------- Tests for format of Done command --------------------------------------

    /*
     * Valid format equivalence partitions for : 1) short command format
     * 2) long command format
     *
     * The two test cases below test one valid command format at a time
     * for each of the two valid possible command formats.
     */

    /*
     * EP: valid format using long command, should remove all task's
     * current categories and add category "Done" in their place.
     *
     * Should return true.
     */

    @Test
    public void markTaskDoneLong_success() throws Exception {
        int taskBossIndex = 2;

        TestTask endedTask = new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Feb 22, 2017 5pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories("Done").build();

        assertEndSuccess(false, taskBossIndex, taskBossIndex, endedTask);
    }

    /*
     * EP: valid format using short command, should remove all task's
     * current categories and add category "Done" in their place.
     *
     * Should return true.
     */

    @Test
    public void endTask_Short_Command_success() throws Exception {
        int taskBossIndex = 2;

        TestTask endedTask = new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Feb 22, 2017 5pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories("Done").build();

        assertEndSuccess(true, taskBossIndex, taskBossIndex, endedTask);
    }

    //---------------- Tests for successfully marking done multiple tasks-----------------------

    @Test
    public void multiple_Ends_Long_Command_success() throws Exception {
        int[] taskBossIndex = {2, 6};

        TestTask endedTaskA = new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Feb 22, 2017 5pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories("Done").build();

        TestTask endedTaskB = new TaskBuilder().withName("Game project player testing").withPriorityLevel("Yes")
                .withStartDateTime("Jan 1, 2017 5pm")
                .withEndDateTime("Nov 28, 2017 5pm")
                .withRecurrence(Frequency.DAILY)
                .withInformation("4th street")
                .withCategories("Done").build();

        TestTask[] tasksEnded = {endedTaskA, endedTaskB};

        assertMultipleEndingsSuccess(false, taskBossIndex, tasksEnded);
        commandBox.runCommand("u");
    }

    @Test
    public void multiple_SpacesInBetween_End_Short_Command_success() throws Exception {
        commandBox.runCommand("t 2       6 ");
        expectedTasksList[1] = new TaskBuilder().withName("Ensure code quality").withPriorityLevel("No")
                .withStartDateTime("Feb 22, 2017 5pm")
                .withEndDateTime("Feb 28, 2017 5pm")
                .withRecurrence(Frequency.MONTHLY)
                .withInformation("michegan ave")
                .withCategories("Done").build();
        expectedTasksList[5] = new TaskBuilder().withName("Game project player testing").withPriorityLevel("Yes")
                .withStartDateTime("Jan 1, 2017 5pm")
                .withEndDateTime("Nov 28, 2017 5pm")
                .withRecurrence(Frequency.DAILY)
                .withInformation("4th street")
                .withCategories("Done").build();

        assertTrue(taskListPanel.isListMatching(expectedTasksList));
    }

    @Test
    public void multiple_TaskEndings_Command_InvalidIndex() {
        commandBox.runCommand("t 1 2 100");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        commandBox.runCommand("t 0 2 3");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void end_NonRecurringTask_Failure() {
        commandBox.runCommand("terminate 1");
        assertResultMessage(EndCommand.NOT_RECURRING);
    }

    //---------------- End of test cases --------------------------------------

    private void assertEndSuccess(boolean isShort, int filteredTaskListIndex, int taskBossIndex,
            TestTask endedTask) {

        //@@author A0144904H
        if (isShort) {
            commandBox.runCommand("t " + filteredTaskListIndex);
        } else {
            commandBox.runCommand("terminate " + filteredTaskListIndex);
        }

        // confirm the list now contains all previous tasks plus the task with updated details
        expectedTasksList[taskBossIndex - 1] = endedTask;

        assertTrue(taskListPanel.isListMatching(expectedTasksList));
        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS ,
                "[" + endedTask + "]"));
    }

    private void assertMultipleEndingsSuccess(boolean isShort, int[] filteredTaskListIndex,
            TestTask[] tasksEnded) {

        StringBuilder sb = new StringBuilder();
        for (int stringIndex = 0; stringIndex < filteredTaskListIndex.length; stringIndex++) {
            sb.append(filteredTaskListIndex[stringIndex]);
            sb.append(" ");
        }

        if (isShort) {
            commandBox.runCommand("t " + sb);
        } else {
            commandBox.runCommand("terminate " + sb);
        }

        for (int index = 0; index < filteredTaskListIndex.length; index++) {
            expectedTasksList[filteredTaskListIndex[index] - 1] = tasksEnded[index];
        }

        StringBuilder sbExpected = new StringBuilder();
        sbExpected.append("[");
        for (int indexExpected = 0; indexExpected < filteredTaskListIndex.length; indexExpected++) {
            sbExpected.append(tasksEnded[indexExpected]);
            if (indexExpected != filteredTaskListIndex.length - 1) {
                sbExpected.append(", ");
            }
        }
        sbExpected.append("]");

        assertTrue(taskListPanel.isListMatching(expectedTasksList));
        assertResultMessage(String.format(MarkDoneCommand.MESSAGE_MARK_TASK_DONE_SUCCESS , sbExpected));
    }

}
