package io.github.marcosstefani;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isWhitespace;
import static java.util.Objects.isNull;

public class AnagramHelper {
    private String[] textList;

    public AnagramHelper() {
        this.clear();
    }

    public int size() {
        return this.textList.length;
    }

    public void clear() {
        this.textList = new String[0];
    }

    public void addText(String text) {
        if (isNull(text) || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty text is not allowed");
        }

        if (containsText(text.trim())) {
            throw new IllegalArgumentException("This text has already been included");
        }

        String[] newTextList = new String[textList.length + 1];
        System.arraycopy(textList, 0, newTextList, 0, textList.length);
        newTextList[textList.length] = text.trim();
        textList = newTextList;
    }

    private boolean containsText(String text) {
        for (String existingText : textList) {
            if (text.equals(existingText)) {
                return true;
            }
        }
        return false;
    }

    private Map<Character, Integer> countCharacters(String text) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char character : text.toCharArray()) {
            if (isLetterOrDigit(character) && !isWhitespace(character)) {
                character = Character.toLowerCase(character);
                charCount.put(character, charCount.getOrDefault(character, 0) + 1);
            }
        }
        return charCount;
    }

    public String[] getArrayTextList() {
        return textList.clone();
    }

    public Set<String> findAnagrams(String inputText) {
        Set<String> anagrams = new HashSet<>();

        Map<Character, Integer> inputCharCount = countCharacters(inputText);

        for (String text : textList) {
            if (!inputText.equals(text) && inputCharCount.equals(countCharacters(text))) {
                anagrams.add(text);
            }
        }

        return anagrams;
    }

    public String[] getTextList() {
        return textList.clone();
    }
}
