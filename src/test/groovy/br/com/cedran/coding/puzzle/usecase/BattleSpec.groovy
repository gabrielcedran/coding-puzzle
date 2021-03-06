package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.model.characters.Character
import br.com.cedran.coding.puzzle.model.characters.Warrior
import br.com.cedran.coding.puzzle.model.creatures.Dragon
import br.com.cedran.coding.puzzle.model.creatures.Monster
import br.com.cedran.coding.puzzle.model.creatures.MonsterFactory
import br.com.cedran.coding.puzzle.model.options.Actions
import br.com.cedran.coding.puzzle.usecase.battle.Battle
import br.com.cedran.coding.puzzle.usecase.battle.DamageEngine
import br.com.cedran.coding.puzzle.usecase.battle.DefaultDamageEngine
import br.com.cedran.coding.puzzle.usecase.battle.EndBattle

class BattleSpec extends BaseSpec {

    Battle battle
    Character character
    Random random
    Monster monster
    DamageEngine damageEngine
    MonsterFactory monsterFactory

    def setup() {
        character = new Warrior()
        random = Mock(Random)
        monster = new Dragon()
        monster.drawing = ["dragon picture"]
        monsterFactory = Mock(MonsterFactory)
        battle = new Battle(screen, keyboard, random, character, monsterFactory)
        damageEngine = new DefaultDamageEngine(character, monster, random, screen)
        battle.damageEngine = damageEngine
    }

    def "Beginning of a battle"() {
        given: "the battle's just begun and there isn't a monster selected"
        battle.monster = null
        and: "the monster factory will return a dragon"
        monsterFactory.getMonster() >> monster
        and: "there isn't an action selected yet"
        battle.action = null
        and: "the user will type A - for attack"
        keyboard.readString() >> 'A'

        when: "the scenario executes"
        Scenario scenario = battle.start()

        then: "a new monster is picked"
        battle.monster instanceof Dragon
        and: "an introduction message about the monster is printed"
        screenMessages[0] == monster.getIntroduction()
        and: "the picture of the monster is printed"
        screenMessages[1] == "dragon picture"
        and: "the life of the monster is printed"
        screenMessages[2] == "Dragon's Life [${monster.lifeRemaining}/${monster.life}]"
        and: "the life bar of the monster is printed"
        verifyLifeBar(3, 20)
        and: "a text giving pointers how to play is printed"
        screenMessages[25] == "Type: "
        screenMessages[26] == "A to Attack | "
        and: "the next scenario is still the battle"
        scenario instanceof Battle
        and: "the damage engine instantiated is default"
        battle.damageEngine instanceof DefaultDamageEngine
    }

    def "Monster noise"() {
        given: "the battle is going on and monster have full life"
        monster.lifeRemaining = 100l
        battle.monster = monster
        and: "it's time to the monster make some impressions"
        random.nextInt(_) >> 1
        and: "there user selected the option Attack in the previous round"
        battle.action = Actions.ATTACK
        and: "the demage caused by that attack is 10"
        random.nextInt(_) >> 10
        and: "the user will type A - to attack again"
        keyboard.readString() >> 'A'

        when: "the scenario executes"
        Scenario scenario = battle.start()

        then: "the picture of the monster is printed"
        screenMessages[0] == "dragon picture"
        and: "a message displaying the monster noise is displayed"
        screenMessages[1] == "${monster.noise}"
        and: "the next scenario is still the battle"
        scenario instanceof Battle
    }

    def "Cause damage to the monster"() {
        given: "the battle is going on and monster have full life"
        monster.lifeRemaining = 100l
        battle.monster = monster
        and: "there user selected the option Attack in the previous round"
        battle.action = Actions.ATTACK
        and: "the damage caused by that attack is 10"
        random.nextInt(_) >> 10
        and: "the user will type A - for attack again"
        keyboard.readString() >> 'A'

        when: "the scenario executes"
        Scenario scenario = battle.start()

        then: "the life of the monster is decrease to 90"
        monster.getLifeRemaining() == 90l
        and: "the picture of the monster is printed"
        screenMessages[0] == "dragon picture"
        and: "a message displaying the damage caused is printed"
        screenMessages[1] == "You caused a damage of 10 points!"
        and: "the life of the monster is printed"
        screenMessages[2] == "Dragon's Life [${monster.lifeRemaining}/${monster.life}]"
        and: "the life bar of the monster is printed"
        verifyLifeBar(3, 18)
        and: "a text giving pointers how to play is printed"
        screenMessages[25] == "Type: "
        screenMessages[26] == "A to Attack | "
        and: "the next scenario is still the battle"
        scenario instanceof Battle
    }

    def "Kill the monster with one damage of the same value of the remaining life"() {
        given: "the battle is going on and the monster has only 5 points of life remaining"
        monster.lifeRemaining = 5l
        battle.monster = monster
        and: "there user selected the option Attack in the previous round"
        battle.action = Actions.ATTACK
        and: "the damage caused by that attack is 5"
        random.nextInt(_) >> 5
        and: "the user will type A - for attack again"
        keyboard.readString() >> 'A'

        when: "the scenario executes"
        Scenario scenario = battle.start()

        then: "the life of the monster is decrease to 0"
        monster.getLifeRemaining() == 0l
        and: "the picture of the monster is printed"
        screenMessages[0] == "dragon picture"
        and: "a message displaying the damage caused is printed"
        screenMessages[1] == "You caused a damage of 5 points!"
        and: "the life of the monster is printed"
        screenMessages[2] == "Dragon's Life [0/${monster.life}]"
        and: "the life bar of the monster is printed"
        verifyLifeBar(3, 0)
        and: "a text to press ENTER to continue is displayed"
        screenMessages[25] == "Press ENTER to continue..."
        and: "the next scenario is still the battle"
        scenario instanceof EndBattle
    }

    def "Kill the monster with one damage higher than remaining life"() {
        given: "the battle is going on and the monster has only 5 points of life remaining"
        monster.lifeRemaining = 5l
        battle.monster = monster
        and: "there user selected the option Attack in the previous round"
        battle.action = Actions.ATTACK
        and: "the damage caused by that attack is 10"
        random.nextInt(_) >> 10
        and: "the user will type A - for attack again"
        keyboard.readString() >> 'A'

        when: "the scenario executes"
        Scenario scenario = battle.start()

        then: "the life of the monster is decrease to 0"
        monster.getLifeRemaining() == 0l
        and: "the picture of the monster is printed"
        screenMessages[0] == "dragon picture"
        and: "a message displaying the damage cause is printed"
        screenMessages[1] == "You caused a damage of 10 points!"
        and: "the life of the monster is printed"
        screenMessages[2] == "Dragon's Life [0/${monster.life}]"
        and: "the life bar of the monster is printed"
        verifyLifeBar(3, 0)
        and: "a text to press ENTER to continue is displayed"
        screenMessages[25] == "Press ENTER to continue..."
        and: "the next scenario is the end battle"
        scenario instanceof EndBattle
    }


    def verifyLifeBar(int startingPosition, int numberOfHashs) {
        assert screenMessages[startingPosition++] == "["
        if (numberOfHashs > 0) {
            startingPosition.upto(startingPosition + numberOfHashs - 1, { position -> assert screenMessages[position] == "#": "Position:" + position })
        }
        if (numberOfHashs < 20) {
            (startingPosition + numberOfHashs).upto(startingPosition + 19, { position -> assert screenMessages[position] == " ": "Position:" + position })
        }
        assert screenMessages[startingPosition + 20] == "]"
        return true
    }

}
