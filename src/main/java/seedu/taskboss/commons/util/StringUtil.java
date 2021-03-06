package seedu.taskboss.commons.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import seedu.taskboss.model.task.ReadOnlyTask;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    private static int INDEX_ONE = 1;
    private static String NUMBERING_DOT = ". ";

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        assert word != null : "Word parameter cannot be null";
        assert sentence != null : "Sentence parameter cannot be null";

        String preppedWord = word.trim();
        assert !preppedWord.isEmpty() : "Word parameter cannot be empty";
        assert preppedWord.split("\\s+").length == 1 : "Word parameter should be a single word";

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        for (String wordInSentence: wordsInPreppedSentence) {
            if (wordInSentence.equalsIgnoreCase(preppedWord)) return true;
        }
        return false;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        assert t != null;
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if s represents an unsigned integer e.g. 1, 2, 3, ... <br>
     * Will return false if the string is:
     * null, empty string, "-1", "0", "+1", and " 2 " (untrimmed) "3 0" (contains whitespace).
     * @param s Should be trimmed.
     */
    public static boolean isUnsignedInteger(String s) {
        return s != null && s.matches("^0*[1-9]\\d*$");
    }

    //@@author A0143157J
    /**
     * Returns a formatted {@code taskList} ArrayList,
     * so that each ReadOnlyTask in {@code taskList} is numbered
     */
    public static String getDesiredArrayListFormat(ArrayList<ReadOnlyTask> taskList) {
        assert taskList != null;
        int i = INDEX_ONE;
        StringBuilder builder = new StringBuilder();
        for (ReadOnlyTask task : taskList) {
            builder.append(i + NUMBERING_DOT).append(task.toString());
            i++;
        }
        return builder.toString();
    }
}
