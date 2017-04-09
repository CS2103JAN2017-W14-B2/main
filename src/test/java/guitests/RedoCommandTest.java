package guitests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.taskboss.commons.core.Messages;
import seedu.taskboss.logic.commands.RedoCommand;
import seedu.taskboss.testutil.TestTask;
import seedu.taskboss.testutil.TestUtil;

//@@author A0138961W
public class RedoCommandTest extends TaskBossGuiTest {

    @Test
    public void redo() {
        //without any last command
        commandBox.runCommand("redo");
        assertResultMessage(RedoCommand.MESSAGE_WITHOUT_PREVIOUS_OPERATION);

        //redo one undo command
        TestTask[] currentList = td.getTypicalTasks();
        TestTask taskToAdd = td.taskI;
        commandBox.runCommand(taskToAdd.getAddCommand());
        commandBox.runCommand("undo");
        TestTask[] expectedList = TestUtil.addTasksToList(currentList, taskToAdd);
        assertRedoCommandSuccess(false, expectedList);

        //redo operation fails when apply delete operation after undo operation
        TestTask[] currentListOne = td.getTypicalTasks();
        TestTask taskToAddOne = td.taskI;
        commandBox.runCommand(taskToAddOne.getAddCommand());
        commandBox.runCommand("undo");
        commandBox.runCommand("delete 1");
        commandBox.runCommand("redo");
        assertResultMessage(RedoCommand.MESSAGE_WITHOUT_PREVIOUS_OPERATION);

        //invalid command
        commandBox.runCommand("redo2");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void redoShortCommand() {

        //without any last command
        commandBox.runCommand("r");
        assertResultMessage(RedoCommand.MESSAGE_WITHOUT_PREVIOUS_OPERATION);

        //redo one undo command
        TestTask[] currentListTwo = td.getTypicalTasks();
        TestTask taskToAddTwo = td.taskI;
        commandBox.runCommand(taskToAddTwo.getAddCommand());
        commandBox.runCommand("undo");
        TestTask[] expectedList = TestUtil.addTasksToList(currentListTwo, taskToAddTwo);
        assertRedoCommandSuccess(true, expectedList);

        //redo operation fails when apply delete operation after undo operation
        TestTask[] currentListThree = td.getTypicalTasks();
        TestTask taskToAddThree = td.taskI;
        commandBox.runCommand(taskToAddThree.getAddCommand());
        commandBox.runCommand("u");
        commandBox.runCommand("d 1");
        commandBox.runCommand("r");
        assertResultMessage(RedoCommand.MESSAGE_WITHOUT_PREVIOUS_OPERATION);

        //invalid command
        commandBox.runCommand("r2");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    private void assertRedoCommandSuccess(boolean isShortCommand, TestTask[] expectedList) {
        if (isShortCommand) {
            commandBox.runCommand("r");
        } else {
            commandBox.runCommand("redo");
        }
        assertTrue(taskListPanel.isListMatching(expectedList));
        assertResultMessage(RedoCommand.MESSAGE_SUCCESS);
    }
}
