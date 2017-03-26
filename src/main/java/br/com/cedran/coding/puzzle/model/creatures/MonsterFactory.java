package br.com.cedran.coding.puzzle.model.creatures;

public class MonsterFactory {

    public Monster getMonster(Integer monsterNumber) {
        Monster monster = null;
        if (monsterNumber == 0) {
            monster = new Dragon();
        }
        return monster;
    }
}
