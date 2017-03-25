package br.com.cedran.coding.puzzle.model.creatures;

import br.com.cedran.coding.puzzle.model.options.TextColors;

public abstract class Monster {

    private Long lifeRemaining;

    public abstract String getName();

    public abstract String getIntroduction();

    public abstract String getNoise();

    public abstract Long getLife();

    public abstract Long getExperience();

    public Long getLifeRemaining() {
        if (lifeRemaining == null) {
            lifeRemaining = getLife();
        }
        return lifeRemaining;
    }

    public void decreaseLifeRemaining(Integer damage) {
        lifeRemaining -= damage;
        if (lifeRemaining < 0) {
            lifeRemaining = 0l;
        }
    }

    void setLifeRemaining(Long lifeRemaining) {
        this.lifeRemaining = lifeRemaining;
    }

    public abstract String[] getDrawing();

    public abstract TextColors getColor();
}
