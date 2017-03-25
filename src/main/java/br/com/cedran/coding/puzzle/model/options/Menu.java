package br.com.cedran.coding.puzzle.model.options;

import java.util.Arrays;

public enum Menu {

    NEW_GAME("New Game", 1), LOAD_GAME("Load Game", 2), QUIT_GAME("Exit", 3);

    Menu(String description, Integer number) {
        this.description = description;
        this.number = number;
    }

    private String description;
    private Integer number;

    public String getDescription() {
        return description;
    }

    public Integer getNumber() {
        return number;
    }

    public static Menu getByNumber(Integer number) {
        return Arrays.stream(Menu.values()).filter(value -> value.getNumber().equals(number)).findFirst().orElse(null);
    }
}
