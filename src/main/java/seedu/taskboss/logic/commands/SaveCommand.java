package seedu.taskboss.logic.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.taskboss.logic.commands.exceptions.CommandException;

//@@author A0138961W
/**
 * Saves the data at specific filepath. Creates filepath if it does not exist
 */

public class SaveCommand extends Command {

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

        try {
            Path path = Paths.get(filepath);
            if (!Files.exists(path)) {
                throw new CommandException(MESSAGE_INVALID_FILEPATH);
            }
            storage.setFilePath(filepath);
            model.saveTaskboss();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (InvalidPathException ipe) {
            throw new CommandException(MESSAGE_INVALID_FILEPATH);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_INVALID_FILEPATH);
        }
    }
}
