package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.taskboss.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import guitests.guihandles.TaskCardHandle;
import seedu.taskboss.commons.core.Messages;
import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.logic.commands.EditCommand;
import seedu.taskboss.model.category.Category;
import seedu.taskboss.model.task.Name;
import seedu.taskboss.model.task.PriorityLevel;
import seedu.taskboss.testutil.TaskBuilder;
import seedu.taskboss.testutil.TestTask;

// TODO: reduce GUI tests by transferring some tests to be covered by lower level tests.
public class EditCommandTest extends TaskBossGuiTest {

    // The list of tasks in the task list panel is expected to match this list.
    // This list is updated with every successful call to assertEditSuccess().
    TestTask[] expectedTasksList = td.getTypicalTasks();

    @Test
    public void edit_allFieldsSpecified_success() throws Exception {
        String detailsToEdit = "n/Alice p/Yes sd/10am Feb 19, 2017 ed/10am Feb 28, 2017 i/123,"
                + " Jurong West Ave 6, #08-111 c/friends";
        int taskBossIndex = 1;

        TestTask editedTask = new TaskBuilder().withName("Alice").withPriorityLevel("Yes")
               .withStartDateTime("10am Feb 19, 2017").withEndDateTime("10am Feb 28, 2017")
               .withInformation("123, Jurong West Ave 6, #08-111").withCategories("friends").build();

        assertEditSuccess(false, taskBossIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    //@@author A0143157J
    // EP: edit all fields
    @Test
    public void edit_allFieldsWithShortCommand_success() throws Exception {

        String detailsToEdit = "n/Amanda p/Yes sd/feb 27 2016 ed/feb 28 2016 i/discuss about life c/relax";
        int taskBossIndex = 1;

        TestTask editedTask = new TaskBuilder().withName("Amanda").withPriorityLevel("Yes")
               .withStartDateTime("feb 27 2016").withEndDateTime("feb 28 2016")
               .withInformation("discuss about life").withCategories("relax").build();

        assertEditSuccess(true, taskBossIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    // EP: edit categories with short command
    @Test
    public void edit_notAllFieldsWithShortCommand_success() throws Exception {
        String detailsToEdit = "c/work c/fun";
        int taskBossIndex = 2;

        TestTask taskToEdit = expectedTasksList[taskBossIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withCategories("work", "fun").build();

        assertEditSuccess(true, taskBossIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    //@@author A0143157J
    // EP: invalid edit command with start date later than end date
    @Test
    public void edit_removeDate_sucess() throws IllegalValueException {
        String detailsToEdit = "sd/";
        int taskBossIndex = 1;

        TestTask taskToEdit = expectedTasksList[taskBossIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withStartDateTime("").build();

        assertEditSuccess(false, taskBossIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    //@@author
    @Test
    public void edit_notAllFieldsSpecified_success() throws Exception {
        String detailsToEdit = "c/sweetie c/bestie";
        int taskBossIndex = 2;

        TestTask taskToEdit = expectedTasksList[taskBossIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withCategories("sweetie", "bestie").build();

        assertEditSuccess(false, taskBossIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_clearCategories_success() throws Exception {
        String detailsToEdit = "c/";
        int taskBossIndex = 2;

        TestTask taskToEdit = expectedTasksList[taskBossIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withCategories().build();

        assertEditSuccess(false, taskBossIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_findThenEdit_success() throws Exception {
        commandBox.runCommand("find n/Elle");

        String detailsToEdit = "n/Belle";
        int filteredTaskListIndex = 1;
        int taskBossIndex = 2;

        TestTask taskToEdit = expectedTasksList[taskBossIndex - 1];
        TestTask editedTask = new TaskBuilder(taskToEdit).withName("Belle").build();

        assertEditSuccess(false, filteredTaskListIndex, taskBossIndex, detailsToEdit, editedTask);
    }

    @Test
    public void edit_missingTaskIndex_failure() {
        commandBox.runCommand("edit n/Bobby");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void edit_invalidTaskIndex_failure() {
        commandBox.runCommand("edit 8 n/Bobby");
        assertResultMessage(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void edit_noFieldsSpecified_failure() {
        commandBox.runCommand("edit 1");
        assertResultMessage(EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void edit_invalidValues_failure() {
        commandBox.runCommand("edit 1 n/*&");
        assertResultMessage(Name.MESSAGE_NAME_CONSTRAINTS);

        commandBox.runCommand("edit 1 p/abcd");
        assertResultMessage(PriorityLevel.MESSAGE_PRIORITY_CONSTRAINTS);

        commandBox.runCommand("edit 1 c/*&");
        assertResultMessage(Category.MESSAGE_CATEGORY_CONSTRAINTS);
    }

    @Test
    public void edit_duplicateTask_failure() {
        commandBox.runCommand("edit 1 n/Alice Pauline p/Yes sd/Feb 18, 2017 5pm 5pm ed/Mar 28, 2017 5pm"
                                + "i/123, Jurong West Ave 6, #08-111 c/friends");

        assertResultMessage(EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    //@@author A0143157J
    // EP: invalid edit command with start date later than end date
    @Test
    public void edit_invalidDates_failure() {
        commandBox.runCommand("edit 3 sd/next fri 5pm ed/tomorrow");

        assertResultMessage(EditCommand.ERROR_INVALID_DATES);
    }

    //@@author
    /**
     * Checks whether the edited task has the correct updated details.
     *
     * @param filteredTaskListIndex index of task to edit in filtered list
     * @param taskBossIndex index of task to edit in TaskBoss.
     *      Must refer to the same task as {@code filteredTaskListIndex}
     * @param detailsToEdit details to edit the task with as input to the edit command
     * @param editedTask the expected task after editing the task's details
     */
    private void assertEditSuccess(boolean isShortCommand, int filteredTaskListIndex, int taskBossIndex,
                                    String detailsToEdit, TestTask editedTask) {
        if (isShortCommand) {
            commandBox.runCommand("e " + filteredTaskListIndex + " " + detailsToEdit);
        } else {
            commandBox.runCommand("edit " + filteredTaskListIndex + " " + detailsToEdit);
        }

        // confirm the new card contains the right data
        TaskCardHandle editedCard = taskListPanel.navigateToTask(editedTask.getName().fullName);
        assertMatching(editedTask, editedCard);

        // confirm the list now contains all previous tasks plus the task with updated details
        expectedTasksList[taskBossIndex - 1] = editedTask;
        assertTrue(taskListPanel.isListMatching(expectedTasksList));
        assertResultMessage(String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }
}
