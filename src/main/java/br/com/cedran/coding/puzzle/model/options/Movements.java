package br.com.cedran.coding.puzzle.model.options;

import java.util.Arrays;

public enum Movements {

    FORWARD("Foward", "W"), BACKWARD("Backward", "S"), LEFT("Left", "A"), RIGHT("Right", "D");

    Movements(String description, String key) {
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

    public static Movements getByKey(String key) {
        return Arrays.stream(Movements.values()).filter(value -> value.getKey().equals(key.toUpperCase())).findFirst().orElse(null);
    }
}
