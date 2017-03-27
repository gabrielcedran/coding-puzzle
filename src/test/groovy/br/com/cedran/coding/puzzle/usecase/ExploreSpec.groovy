package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.gateway.SaveGateway
import br.com.cedran.coding.puzzle.model.characters.Character
import br.com.cedran.coding.puzzle.model.characters.Warrior
import br.com.cedran.coding.puzzle.model.options.Movements
import br.com.cedran.coding.puzzle.usecase.battle.Battle

class ExploreSpec extends BaseSpec {

    Explore exploreScenario
    Character character
    Random random
    SaveGateway saveGateway

    def setup() {
        character = new Warrior()
        character.drawing = ["character drawing"];
        random = Mock(Random)
        saveGateway = Mock(SaveGateway)
        exploreScenario = new Explore(screen, keyboard, character, null, random, saveGateway)
    }

    def "Execute explore scenario without previous movement"() {
        given: "There is no previous movement and the user will select the movement 'W'"
        keyboard.readString() >> "W"
        and: "the character contains 0 steps"
        character.steps = 0

        when: "the explore scenario is executed"
        Scenario useCase = exploreScenario.start()

        then: "the character draw is displayed"
        screenMessages[0] == "character drawing"
        and: "the current number of steps and experience are displayed"
        screenMessages[1] == "Number of steps: 0 | Experience: 0"
        and: "instructions of commands is displayed"
        screenMessages[2] == "W - forward | S - backward | A -  left | D - right | Q - Menu"
        and: "the number of steps is increased"
        character.steps == 1l
        and: "the next screen returned is explore scenario"
        useCase instanceof Explore
    }

    def "Move forwards"() {
        given: "the user selected in the previous scene the key 'W'"
        exploreScenario.lastMovement = Movements.FORWARD
        and: "he will select the key 'S' for the next scene"
        keyboard.readString() >> "S"
        and: "the character contains 4 steps"
        character.steps = 4

        when: "the explore scenario is executed"
        Scenario useCase = exploreScenario.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the current number of steps and experience are displayed"
        screenMessages[1] == "Number of steps: 4 | Experience: 0 | Moving " + Movements.FORWARD.getDescription()
        and: "the number of steps is increased"
        character.steps == 5l
        and: "the next screen returned is explore scenario"
        useCase instanceof Explore
    }

    def "Move backwards and find a monster"() {
        given: "the user selected in the previous scene the key 'S'"
        exploreScenario.lastMovement = Movements.BACKWARD
        and: "he will select the key 'S' for the next scene"
        keyboard.readString() >> "S"
        and: "the next steps find a monster"
        random.nextInt(_) >> 1
        and: "the character contains 1000 steps and 1 point of experience"
        character.steps = 1000
        character.experience = 1

        when: "the explore scenario is executed"
        Scenario useCase = exploreScenario.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the current number of steps and experience are displayed"
        screenMessages[1] == "Number of steps: 1000 | Experience: 1 | Moving " + Movements.BACKWARD.getDescription()
        and: "the number of steps is increased"
        character.steps == 1001l
        and: "the number of experience does not change"
        character.experience == 1l
        and: "the next screen returned is explore scenario"
        useCase instanceof Battle
    }

    def "Move left"() {
        given: "the user selected in the previous scene the key 'A'"
        exploreScenario.lastMovement = Movements.LEFT
        and: "he will select the key 'D' for the next scene"
        keyboard.readString() >> "D"
        and: "the character contains 1000000 steps"
        character.steps = 10000001

        when: "the explore scenario is executed"
        Scenario useCase = exploreScenario.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the current number of steps and experience are displayed"
        screenMessages[1] == "Number of steps: 10000001 | Experience: 0 | Moving " + Movements.LEFT.getDescription()
        and: "the number of steps is increased"
        character.steps == 10000002l
        and: "the next screen returned is explore scenario"
        useCase instanceof Explore
    }

    def "Move right and quit game"() {
        given: "the user selected in the previous scene the key 'D'"
        exploreScenario.lastMovement = Movements.RIGHT
        and: "he will select the key 'Q' for the next scene"
        keyboard.readString() >> "Q"
        and: "the character contains 3 steps"
        character.steps = 3

        when: "the explore scenario is executed"
        Scenario useCase = exploreScenario.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the current number of steps and experience are displayed"
        screenMessages[1] == "Number of steps: 3 | Experience: 0 | Moving " + Movements.RIGHT.getDescription()
        and: "the number of steps isn't increased"
        character.steps == 3l
        and: "the next screen returned is show menu"
        useCase instanceof ShowMenu
    }

    def "Invalid movement"() {
        given: "the user selected in the previous scene the key 'I'"
        exploreScenario.lastMovement = null
        and: "he will select the key 'II' for the next scene"
        keyboard.readString() >> "II"
        and: "the character contains 3 steps"
        character.steps = 3

        when: "the explore scenario is executed"
        Scenario useCase = exploreScenario.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the current number of steps and experience are displayed"
        screenMessages[1] == "Number of steps: 3 | Experience: 0"
        and: "the number of steps isn't increased"
        character.steps == 3l
        and: "the next screen returned is show menu"
        useCase instanceof Explore
    }
}
