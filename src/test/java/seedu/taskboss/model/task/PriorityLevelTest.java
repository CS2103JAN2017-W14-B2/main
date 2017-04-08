package seedu.taskboss.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@@author A0144904H
public class PriorityLevelTest {
    @Test
    public void isValidPriorityLevel() {

        // invalid priority level
        assertFalse(PriorityLevel.isValidPriorityLevel("priorityLevel")); // wrong input
        assertFalse(PriorityLevel.isValidPriorityLevel("9")); // numeric input

        // valid priority level
        assertTrue(PriorityLevel.isValidPriorityLevel(""));
        assertTrue(PriorityLevel.isValidPriorityLevel("Yes"));
        assertTrue(PriorityLevel.isValidPriorityLevel("No"));
        assertTrue(PriorityLevel.isValidPriorityLevel("Y"));
        assertTrue(PriorityLevel.isValidPriorityLevel("N"));
        assertTrue(PriorityLevel.isValidPriorityLevel("YES"));
        assertTrue(PriorityLevel.isValidPriorityLevel("NO"));
        assertTrue(PriorityLevel.isValidPriorityLevel("y"));
        assertTrue(PriorityLevel.isValidPriorityLevel("n"));
        assertTrue(PriorityLevel.isValidPriorityLevel("YeS"));
        assertTrue(PriorityLevel.isValidPriorityLevel("yES"));
        assertTrue(PriorityLevel.isValidPriorityLevel("nO"));
        assertTrue(PriorityLevel.isValidPriorityLevel("yEs"));
        assertTrue(PriorityLevel.isValidPriorityLevel("yes"));
        assertTrue(PriorityLevel.isValidPriorityLevel("no"));
        assertTrue(PriorityLevel.isValidPriorityLevel("yeS"));
        assertTrue(PriorityLevel.isValidPriorityLevel("YEs"));

    }
}
