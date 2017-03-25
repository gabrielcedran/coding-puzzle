package br.com.cedran.coding.puzzle.model.characters;

public class CharacterFactory {
    private static Character instance;

    public static Character getInstance() {
        if (instance == null) {
            instance = new Warrior();
        }
        return instance;
    }
}
