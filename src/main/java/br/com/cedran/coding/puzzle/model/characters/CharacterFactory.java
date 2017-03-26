package br.com.cedran.coding.puzzle.model.characters;

public class CharacterFactory {

    public Character getCharacter(String characterType) {
        Character character = null;
        if ("WARRIOR".equals(characterType)) {
            character = new Warrior();
        }
        return character;
    }
}
