package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.flashcard.Face;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.Statistics;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Flashcard}.
 */
class JsonAdaptedFlashcard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Flashcard's %s field is missing!";

    private final String frontFace;
    private final String backFace;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String statistics;

    /**
     * Constructs a {@code JsonAdaptedFlashcard} with the given flashcard details.
     */
    @JsonCreator
    public JsonAdaptedFlashcard(@JsonProperty("frontFace") String frontFace, @JsonProperty("backFace") String backFace,
                                @JsonProperty("statistics") String statistics,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.frontFace = frontFace;
        this.backFace = backFace;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.statistics = statistics;
    }

    /**
     * Converts a given {@code Flashcard} into this class for Jackson use.
     */
    public JsonAdaptedFlashcard(Flashcard source) {
        frontFace = source.getFrontFace().text;
        backFace = source.getBackFace().text;
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
        statistics = source.getStatistics().toString();
    }

    /**
     * Converts this Jackson-friendly adapted flashcard object into the model's {@code Flashcard} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted flashcard.
     */
    public Flashcard toModelType() throws IllegalValueException {
        final List<Tag> flashcardTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            flashcardTags.add(tag.toModelType());
        }

        if (frontFace == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Face.class.getSimpleName()));
        }
        if (!Face.isValidFace(frontFace)) {
            throw new IllegalValueException(Face.MESSAGE_CONSTRAINTS);
        }
        final Face modelFrontFace = new Face(frontFace);


        if (backFace == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Face.class.getSimpleName()));
        }
        if (!Face.isValidFace(backFace)) {
            throw new IllegalValueException(Face.MESSAGE_CONSTRAINTS);
        }
        final Face modelBackFace = new Face(backFace);

        if (statistics == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Statistics.class.getSimpleName()));
        }
        if (!Statistics.isValidStatistics(statistics)) {
            throw new IllegalValueException(Statistics.MESSAGE_CONSTRAINTS);
        }
        final Statistics modelStatistics = new Statistics(statistics);


        final Set<Tag> modelTags = new HashSet<>(flashcardTags);
        return new Flashcard(modelFrontFace, modelBackFace, modelStatistics, modelTags);
    }

}
