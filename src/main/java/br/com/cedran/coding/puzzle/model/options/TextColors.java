package br.com.cedran.coding.puzzle.model.options;

import java.util.Arrays;

public enum TextColors {

    RESET("\u001b[0m", -1, null), RED("\u001b[31m", 1, "Red"), GREEN("\u001b[32m", 2, "Green"), BLUE("\u001b[34m", 3, "Blue"), WHITE("\u001b[37m", 4, "White"), YELLOW("\u001b[33m", 5, "Yellow");

    private String ascii;
    private Integer number;
    private String description;

    TextColors(String ascii, Integer number, String description) {
        this.ascii = ascii;
        this.number = number;
        this.description = description;
    }

    public String getAscii() {
        return ascii;
    }

    public Integer getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static TextColors getByNumber(Integer number) {
        return Arrays.stream(TextColors.values()).filter(value -> value.getNumber().equals(number)).findFirst().orElse(null);
    }
}
