package seedu.taskboss.logic.parser;

import static seedu.taskboss.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskboss.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.taskboss.logic.parser.CliSyntax.PREFIX_INFORMATION;
import static seedu.taskboss.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.taskboss.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.NoSuchElementException;

import seedu.taskboss.commons.exceptions.IllegalValueException;
import seedu.taskboss.logic.commands.AddCommand;
import seedu.taskboss.logic.commands.Command;
import seedu.taskboss.logic.commands.IncorrectCommand;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     */
    public Command parse(String args) {
        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(PREFIX_PRIORITY, PREFIX_EMAIL, PREFIX_INFORMATION, PREFIX_TAG);
        argsTokenizer.tokenize(args);
        try {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    argsTokenizer.getValue(PREFIX_PRIORITY).get(),
                    argsTokenizer.getValue(PREFIX_EMAIL).get(),
                    argsTokenizer.getValue(PREFIX_INFORMATION).get(),
                    ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_TAG))
            );
        } catch (NoSuchElementException nsee) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

}
