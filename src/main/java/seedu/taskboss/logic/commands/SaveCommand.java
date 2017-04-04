package seedu.taskboss.logic.commands;

import java.io.File;
import java.io.IOException;

import seedu.taskboss.logic.commands.exceptions.CommandException;

//@@author A0138961W
/**
 * Saves the data at specific filepath. Creates filepath if it does not exist
 */

public class SaveCommand extends Command {

    private static final String SYMBOL_ASTERISK = "*";
    private static final String SYMBOL_CARET = "^";
    private static final String SYMBOL_HASH = "#";
    private static final String SYMBOL_PLUS = "+";
    private static final String SYMBOL_SLASH = "/";
    public static final String COMMAND_WORD = "save";
    public static final String COMMAND_WORD_SHORT = "sv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                + "Saves the data at specific filepath.\n"
                + "Example: " + COMMAND_WORD
                + " C://user/desktop/taskboss";

    public static final String MESSAGE_SUCCESS = "The data has been saved!";

    public static final String MESSAGE_INVALID_FILEPATH = "The filepath is invalid.";

    private final String filepath;

    public SaveCommand(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert storage != null;

        File f = new File(filepath);

        if (!filepath.contains(SYMBOL_SLASH) || !f.isDirectory()) {
            throw new CommandException(MESSAGE_INVALID_FILEPATH);
        }

        if (filepath.contains(SYMBOL_PLUS) || filepath.contains (SYMBOL_HASH) ||
                filepath.contains (SYMBOL_CARET) || filepath.contains (SYMBOL_ASTERISK)) {
            throw new CommandException(MESSAGE_INVALID_FILEPATH);
        }

        try {
            storage.setFilePath(filepath);
            model.saveTaskboss();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_INVALID_FILEPATH);
        }
    }
}
