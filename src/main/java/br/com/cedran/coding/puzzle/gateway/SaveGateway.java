package br.com.cedran.coding.puzzle.gateway;

import br.com.cedran.coding.puzzle.model.characters.Character;

public interface SaveGateway {

    void saveCharacter(Character character);

    Character loadCharacter();
}
