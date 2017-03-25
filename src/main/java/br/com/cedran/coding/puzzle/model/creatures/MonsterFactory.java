package br.com.cedran.coding.puzzle.model.creatures;

public class MonsterFactory {

    public Monster getMonster() {
        return new Dragon();
    }
}
