package seedu.address.model.flashcard;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class FaceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Face(null));
    }

    @Test
    public void constructor_invalidFace_throwsIllegalArgumentException() {
        String invalidFace = " ";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Face(invalidFace));
    }

    @Test
    public void isValidFace() {
        // null face
        Assert.assertThrows(NullPointerException.class, () -> Face.isValidFace(null));

        // invalid faces
        assertFalse(Face.isValidFace("")); // empty string
        assertFalse(Face.isValidFace(" ")); // spaces only

        // valid addresses
        assertTrue(Face.isValidFace("Hello"));
        assertTrue(Face.isValidFace("你好"));
    }
}
