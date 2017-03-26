package br.com.cedran.coding.puzzle.model.creatures;

import java.util.Random;

public class MonsterFactory {

    private Random random;

    public MonsterFactory(Random random) {
        this.random = random;
    }

    public Monster getMonster() {
        Integer monsterNumber = random.nextInt(1);
        Monster monster = null;
        if (monsterNumber == 0) {
            monster = new Dragon();
        }
        return monster;
    }
}
