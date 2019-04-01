package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalFlashcards.getTypicalCardCollection;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests and unit tests for SmartCommand.
 */
public class SmartCommandTest {

    private Model model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_inQuiz_success() throws CommandException {
        new QuizCommand().execute(model, commandHistory);
        new SmartCommand().execute(model, commandHistory);
        assertEquals((int) model.getQuizMode(), 1);
        new SmartCommand().execute(model, commandHistory);
        assertEquals((int) model.getQuizMode(), -1);
    }

    @Test
    public void execute_notInQuiz_failure() {
        assertCommandFailure(new SmartCommand(), model, commandHistory, "");
    }

}