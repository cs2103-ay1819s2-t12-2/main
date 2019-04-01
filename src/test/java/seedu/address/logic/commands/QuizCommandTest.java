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
 * Contains integration tests and unit tests for QuizCommand.
 */
public class QuizCommandTest {

    private Model model = new ModelManager(getTypicalCardCollection(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_quiz_success() throws CommandException {
        QuizCommand command = new QuizCommand();
        command.execute(model, commandHistory);
        assertEquals((int) model.getQuizMode(), -1);
        assertEquals(model.getQuizFlashcards().size(), model.getFilteredFlashcardList().size() - 1);
    }


    @Test
    public void execute_inQuizMode_failure() throws CommandException {
        QuizCommand command = new QuizCommand();
        model.setQuizMode(-1);
        assertCommandFailure(command, model, commandHistory, QuizCommand.MESSAGE_QUIZ_FAILURE_IN_QUIZ);
    }

    @Test
    public void execute_emptyList_failure() throws CommandException {
        QuizCommand command = new QuizCommand();
        model.updateFilteredFlashcardList((x) -> false);
        assertCommandFailure(command, model, commandHistory, QuizCommand.MESSAGE_QUIZ_FAILURE_EMPTY);
    }

}