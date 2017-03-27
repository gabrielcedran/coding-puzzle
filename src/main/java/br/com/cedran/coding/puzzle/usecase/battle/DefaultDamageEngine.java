package br.com.cedran.coding.puzzle.usecase.battle;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;

public class DefaultDamageEngine extends DamageEngine {

    public DefaultDamageEngine(Character character, Monster monster, Random random, OutputGateway output) {
        super(character, monster, random, output);
    }

    @Override
    public void calculate() {
        Integer damage = random.nextInt(10);
        output.println("You caused a damage of " + damage + " points!");
        monster.decreaseLifeRemaining(damage);
    }
}
