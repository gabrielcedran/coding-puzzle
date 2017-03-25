package br.com.cedran.coding.puzzle.model.options;

import java.util.Arrays;

public enum TextColors {

    RESET("\u001b[0m", -1), RED("\u001b[31m", 1), GREEN("\u001b[32m", 2), BLUE("\u001b[34m", 3), WHITE("\u001b[37m", 4), YELLOW("\u001b[33m", 5);

    private String ascii;
    private Integer number;

    TextColors(String ascii, Integer number) {
        this.ascii = ascii;
        this.number = number;
    }

    public String getAscii() {
        return ascii;
    }

    public Integer getNumber() {
        return number;
    }

    public static TextColors getByNumber(Integer number) {
        return Arrays.stream(TextColors.values()).filter(value -> value.getNumber().equals(number)).findFirst().orElse(null);
    }
}
