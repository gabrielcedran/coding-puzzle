package br.com.cedran.coding.puzzle.usecase.battle;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;
import br.com.cedran.coding.puzzle.model.options.Actions;

public abstract class DamageEngine {

    Character character;

    Monster monster;

    Actions action;

    Random random;

    OutputGateway output;

    public DamageEngine(Character character, Monster monster, Random random, OutputGateway output) {
        this.character = character;
        this.monster = monster;
        this.random = random;
        this.output = output;
    }

    public void execute(Actions action) {
        if (action == null) {
            return;
        }
        this.action = action;
        this.calculate();
        this.action = null;
    }

    abstract void calculate();

}
