package br.com.cedran.coding.puzzle.model.characters;

import br.com.cedran.coding.puzzle.model.options.TextColors;

public abstract class Character {

    private String name;
    private TextColors color;
    private Long numberOfSteps;
    private Long experience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TextColors getColor() {
        return color;
    }

    public void setColor(TextColors color) {
        this.color = color;
    }

    public Long getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(Long numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public abstract String[] getDrawing();
}
