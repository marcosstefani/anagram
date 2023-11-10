package io.github.marcosstefani;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldNotRepeatTheInputTextAsTrim() {
        AnagramHelper helper = new AnagramHelper();
        final String something = "something";
        helper.addText(something);
        assertThrows(IllegalArgumentException.class, () -> helper.addText(something));
        assertThrows(IllegalArgumentException.class, () -> helper.addText(" " + something));
        assertThrows(IllegalArgumentException.class, () -> helper.addText(" " + something + " "));
    }

    @Test
    void shouldCompareTwoAnagramTextsAndReturn() {
        AnagramHelper helper = new AnagramHelper();
        helper.addText("silent");
        helper.addText("listen");
        Set<String> result = helper.findAnagrams("silent");
        assertEquals(result.size(), 1);
        assertEquals("listen", result.toArray()[0]);
    }

    @Test
    void shouldCompareTwoAnagramTextsAndReturnCaseInsensitive() {
        AnagramHelper helper = new AnagramHelper();
        final String silent = "silent";
        helper.addText(silent);
        final String liStEn = "LiStEn";
        helper.addText(liStEn);
        Set<String> result = helper.findAnagrams(silent);
        assertEquals(result.size(), 1);
        assertEquals(liStEn, result.toArray()[0]);
    }

    @Test
    void shouldCompareTwoAnagramTextWithSpacesAndReturn() {
        AnagramHelper helper = new AnagramHelper();
        helper.addText("William Shakespeare");
        helper.addText("I am a weakish speller");
        Set<String> result = helper.findAnagrams("William Shakespeare");
        assertEquals(result.size(), 1);
        assertEquals("I am a weakish speller", result.toArray()[0]);
    }

    @Test
    void shouldCompareTwoAnagramTextsWithSymbolAndReturn() {
        AnagramHelper helper = new AnagramHelper();
        helper.addText("She Sells Sanctuary");
        helper.addText("Santa; shy, less cruel");
        helper.addText("Satan; cruel, less shy");
        Set<String> result = helper.findAnagrams("She Sells Sanctuary");
        assertEquals(result.size(), 2);
        assertTrue(result.contains("Santa; shy, less cruel"));
        assertTrue(result.contains("Satan; cruel, less shy"));
    }

}