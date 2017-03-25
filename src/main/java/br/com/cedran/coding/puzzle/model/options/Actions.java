package br.com.cedran.coding.puzzle.model.options;

import java.util.Arrays;

public enum Actions {
    ATTACK("Attack", "A");

    Actions(String description, String key) {
        this.description = description;
        this.key = key;
    }

    private String description;
    private String key;

    public String getDescription() {
        return description;
    }

    public String getKey() {
        return key;
    }

    public static Actions getByKey(String key) {
        return Arrays.stream(Actions.values()).filter(value -> value.getKey().equals(key.toUpperCase())).findFirst().orElse(null);
    }
}
