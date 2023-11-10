package io.github.marcosstefani;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnagramHelperTest {

    @Test
    void shouldCreateWithSizeZero() {
        AnagramHelper helper = new AnagramHelper();
        assertEquals(0, helper.size());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTextIsNullOrEmpty() {
        AnagramHelper helper = new AnagramHelper();
        assertThrows(IllegalArgumentException.class, () -> helper.addText(null));
        assertThrows(IllegalArgumentException.class, () -> helper.addText(""));
        assertThrows(IllegalArgumentException.class, () -> helper.addText("     "));
    }

    @Test
    void shouldAddOneElementIfNotNull() {
        AnagramHelper helper = new AnagramHelper();
        helper.addText("something");
        assertEquals(1, helper.size());
    }

    @Test
    void shouldCreateAsManyAnagramsAsNecessary() {
        AnagramHelper helper = new AnagramHelper();
        int random = (int) (Math.random() * (50 - 10)) + 10;
        for (int idx = 0; idx < random; idx++) {
            byte[] array = new byte[10];
            new Random().nextBytes(array);
            helper.addText(new String(array, StandardCharsets.UTF_8));
        }
        Assertions.assertEquals(random, helper.size());
    }

    @Test
    void shouldClearTheListAsNecessary() {
        AnagramHelper helper = new AnagramHelper();
        int random = (int) (Math.random() * (50 - 10)) + 10;
        for (int idx = 0; idx < random; idx++) {
            byte[] array = new byte[10];
            new Random().nextBytes(array);
            helper.addText(new String(array, StandardCharsets.UTF_8));
        }
        helper.clear();
        Assertions.assertEquals(0, helper.size());
    }

    @Test
    void shouldNotRepeatTheInputText() {
        AnagramHelper helper = new AnagramHelper();
        final String something = "something";
        helper.addText(something);
        assertThrows(IllegalArgumentException.class, () -> helper.addText(something));
    }

}