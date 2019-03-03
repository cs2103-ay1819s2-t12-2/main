package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.CardCollection;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableCardCollectionTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCardCollectionTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalCardsCardCollection.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidCardCardCollection.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateCardCardCollection.json");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableCardCollection dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableCardCollection.class).get();
        CardCollection cardCollectionFromFile = dataFromFile.toModelType();
        CardCollection typicalPersonsCardCollection = TypicalPersons.getTypicalCardCollection();
        assertEquals(cardCollectionFromFile, typicalPersonsCardCollection);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCardCollection dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializableCardCollection.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableCardCollection dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializableCardCollection.class).get();
        thrown.expect(IllegalValueException.class);
        thrown.expectMessage(JsonSerializableCardCollection.MESSAGE_DUPLICATE_PERSON);
        dataFromFile.toModelType();
    }

}
