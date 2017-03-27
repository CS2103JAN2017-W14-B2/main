package guitests;

import static org.junit.Assert.assertTrue;
import static seedu.taskboss.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.logic.commands.AddCommand;
import seedu.taskboss.logic.commands.RenameCategoryCommand;
import seedu.taskboss.logic.parser.RenameCategoryCommandParser;
import seedu.taskboss.testutil.TaskBuilder;
import seedu.taskboss.testutil.TestTask;

//@@author A0144904H
public class RenameCategoryCommandTest extends TaskBossGuiTest {

    @Test
    public void renameCategory_Long_Command_success() throws IllegalValueException {
        assertRenameCategoryResult("name friends Project");
    }

    @Test
    public void renameCategory_Short_Command_success() throws IllegalValueException {
        assertRenameCategoryResult("n friends Project");
    }

    private void assertRenameCategoryResult(String command) throws IllegalValueException {
        TestTask sampleA;
        TestTask sampleB;
        sampleA = new TaskBuilder().withName("Attend wedding")
                .withInformation("123, Jurong West Ave 6, #08-111")
                .withPriorityLevel("Yes")
                .withStartDateTime("Feb 18, 2017 5pm")
                .withEndDateTime("Mar 28, 2017 5pm")
                .withCategories("Project", AddCommand.DEFAULT).build();
        sampleB = new TaskBuilder().withName("Birthday party")
                .withInformation("311, Clementi Ave 2, #02-25")
                .withPriorityLevel("No")
                .withStartDateTime("Feb 23, 2017 10pm")
                .withEndDateTime("Jun 28, 2017 5pm")
                .withCategories("owesMoney", "Project").build();

        TestTask[] taskListExpected = {sampleA, sampleB};
        commandBox.runCommand(command);
        assertResultMessage(RenameCategoryCommand.MESSAGE_SUCCESS);
        assertTrue(taskListPanel.isListMatching(taskListExpected));
    }


    @Test
    public void rename_unsuccessful() {

        //old category name == new category name
        commandBox.runCommand("name friends friends");
        assertResultMessage(RenameCategoryCommandParser.ERROR_SAME_FIELDS);

        //invalid number of fields
        commandBox.runCommand("name friends bestfriends forever");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCategoryCommand.MESSAGE_USAGE));

        //category name with a single non-alphanumerical character
        commandBox.runCommand("name owesMoney myMoney!");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RenameCategoryCommandParser.ERROR_NON_ALPHANUMERIC));

        //category name with all non-alphanumerical characters
        commandBox.runCommand("name owesMoney !!!");
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                RenameCategoryCommandParser.ERROR_NON_ALPHANUMERIC));

        //category name does not exist
        commandBox.runCommand("name superman batman");
        assertResultMessage("[superman] " + RenameCategoryCommand.MESSAGE_DOES_NOT_EXIST_CATEGORY);

        //category name is AllTasks
        //@@author A0144904H
        commandBox.runCommand("name AllTasks batman");
        assertResultMessage(RenameCategoryCommand.MESSAGE_ALL_TASK_CATEGORY_CANNOT_RENAME);

        //category name is Done
        //@@author A0144904H
        commandBox.runCommand("name Done batman");
        assertResultMessage(RenameCategoryCommand.MESSAGE_DONE_CATEGORY_CANNOT_RENAME);
    }
}
