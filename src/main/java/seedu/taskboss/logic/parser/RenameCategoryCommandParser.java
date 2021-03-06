package seedu.taskboss.logic.parser;

import static seedu.taskboss.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.taskboss.commons.core.LogsCenter;
import seedu.taskboss.logic.commands.Command;
import seedu.taskboss.logic.commands.IncorrectCommand;
import seedu.taskboss.logic.commands.RenameCategoryCommand;

//@@author A0143157J
/**
 * Parses input arguments and creates a new RenameCategoryCommand object
 */
public class RenameCategoryCommandParser {

    private final Logger logger = LogsCenter.getLogger(RenameCategoryCommandParser.class);

    private static final int NUM_NO_CATEGORIES = 0;
    private static final int NUM_CATEGORIES_PARAM = 2;

    private static final int INDEX_OLD_CATEGORY = 0;
    private static final int INDEX_NEW_CATEGORY = 1;

    private static final String REGEX_ALPHANUMERIC = "[A-Za-z0-9]+";

    public static final String ERROR_NON_ALPHANUMERIC = "Category names should be alphanumeric.";
    public static final String ERROR_SAME_FIELDS = "Old and new category names are the same.";

    public Command parse(String args) {
        String[] categories = ParserUtil.parseRenameCategory(args);
        if (categories.length != NUM_CATEGORIES_PARAM || categories.length == NUM_NO_CATEGORIES) {
            logger.info("Number of categories entered in name command is not two. Returning IncorrectCommand.");
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCategoryCommand.MESSAGE_USAGE));
        }
        String oldCategory = categories[INDEX_OLD_CATEGORY];
        String newCategory = categories[INDEX_NEW_CATEGORY];

        if (!oldCategory.matches(REGEX_ALPHANUMERIC) ||
            !newCategory.matches(REGEX_ALPHANUMERIC)) {
            logger.info("User attempted to rename non-alphanumeric categories. Returning IncorrectCommand.");
            return new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ERROR_NON_ALPHANUMERIC));
        } else if (oldCategory.equals(newCategory)) {
            logger.info("User entered same categories for name command. Returning IncorrectCommand.");
            return new IncorrectCommand(ERROR_SAME_FIELDS);
        }

        return new RenameCategoryCommand(oldCategory, newCategory);
    }
}
