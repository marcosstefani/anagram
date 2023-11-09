package io.github.marcosstefani;

import java.util.Objects;

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
        if (Objects.isNull(text) || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty text is not allowed");
        }
        String[] newArray = new String[this.textList.length +1];
        System.arraycopy(textList, 0, newArray, 0, this.textList.length);
        newArray[newArray.length -1] = text;
        textList = newArray;
    }
}
