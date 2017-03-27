package br.com.cedran.coding.puzzle.usecase.battle;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;

public class DamageEngineFactory {

    private Character character;

    private Monster monster;

    private Random random;

    private OutputGateway output;

    public DamageEngineFactory(Character character, Monster monster, Random random, OutputGateway output) {
        this.character = character;
        this.monster = monster;
        this.random = random;
        this.output = output;
    }

    public DamageEngine getInstance(Monster monster) {
        return new DefaultDamageEngine(this.character, this.monster, this.random, this.output);
    }
}
