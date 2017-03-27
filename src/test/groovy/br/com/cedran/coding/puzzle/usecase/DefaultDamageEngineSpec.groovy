package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.model.characters.Character
import br.com.cedran.coding.puzzle.model.characters.Warrior
import br.com.cedran.coding.puzzle.model.creatures.Dragon
import br.com.cedran.coding.puzzle.model.creatures.Monster
import br.com.cedran.coding.puzzle.usecase.battle.DefaultDamageEngine

class DefaultDamageEngineSpec extends BaseSpec {

    DefaultDamageEngine defaultDamageEngine
    Character character
    Monster monster
    Random random

    def setup() {
        character = new Warrior()
        monster = new Dragon()
        random = Mock(Random)
        defaultDamageEngine = new DefaultDamageEngine(character, monster, random, screen)
    }

    def "Calculate damage"() {
        given: "a monster with 100 points of life remaining"
        monster.lifeRemaining = 100l
        and: "a damage that will cause 10 points"
        random.nextInt(_) >> 10

        when: "the use case executes"
        defaultDamageEngine.calculate()

        then: "the life remaining of the monster is decrease in 10 points"
        monster.lifeRemaining == 90l
        and: "a message displayed the damage cause is printed"
        screenMessages[0] == "You caused a damage of 10 points!"
    }

    def "Calculate damage higher than available remaining life"() {
        given: "a monster with 5 points of life remaining"
        monster.lifeRemaining = 5l
        and: "a damage that will cause 10 points"
        random.nextInt(_) >> 10

        when: "the use case executes"
        defaultDamageEngine.calculate()

        then: "the life remaining of the monster is decrease in 10 points"
        monster.lifeRemaining == 0l
        and: "a message displayed the damage cause is printed"
        screenMessages[0] == "You caused a damage of 10 points!"
    }
}