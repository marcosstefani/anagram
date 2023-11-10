package io.github.marcosstefani;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AnagramHelper {
    private Set<String> textList;

    public AnagramHelper() {
        this.clear();
    }

    public int size() {
        return this.textList.size();
    }

    public void clear() {
        this.textList = new HashSet<>();
    }

    public void addText(String text) {
        if (Objects.isNull(text) || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty text is not allowed");
        }

        if (textList.contains(text)) {
            throw new IllegalArgumentException("This text has already been included");
        }

        textList.add(text);
    }
}
