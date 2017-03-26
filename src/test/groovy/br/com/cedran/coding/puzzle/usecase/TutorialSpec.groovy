package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.model.characters.Character
import br.com.cedran.coding.puzzle.model.characters.Warrior
import br.com.cedran.coding.puzzle.model.options.Actions
import br.com.cedran.coding.puzzle.model.options.Movements

class TutorialSpec extends BaseSpec {

    Tutorial tutorial
    Character character

    def setup() {
        character = new Warrior()
        character.drawing = ["character drawing"]
        tutorial = new Tutorial(screen, keyboard, character)
    }

    def "Tutorial for new players"() {
        given: "a character with zero steps"

        when: "the tutorial screen is executed"
        Scenario useCase = tutorial.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "messages teaching how to play are displayed"
        screenMessages[1] == "Before you start, here are some tips:"
        validateCommonMessages()
        and: "the explore scenario is returned"
        useCase instanceof Explore
    }

    def "Tutorial for newbies players"() {
        given: "a character with less than 10 steps"
        character.addStep()

        when: "the tutorial screen is executed"
        Scenario useCase = tutorial.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "messages remembering the player how to play are displayed"
        screenMessages[1] == "It seems that you haven't played much yet, therefore, lets remember how to play:"
        validateCommonMessages()
        and: "the explore scenario is returned"
        useCase instanceof Explore
    }

    private void validateCommonMessages() {
        assert screenMessages[2] == "To walk around use following keys:"
        assert screenMessages[3] == Movements.FORWARD.key + " - to go forward"
        assert screenMessages[4] == Movements.BACKWARD.key + " - to go backward"
        assert screenMessages[5] == Movements.LEFT.key + " - to go to the left"
        assert screenMessages[6] == Movements.RIGHT.key + " - to go to the right"
        assert screenMessages[7] == "If you find any monster use " + Actions.ATTACK.key + " to attack"
        assert screenMessages[8] == "If you want to go back to menu press Q"
        assert screenMessages[9] == "As this goes by turn, after selecting a movement, always press ENTER"
        assert screenMessages[10] == "If you type more than one option, that movement will be discarded"
        assert screenMessages[11] == "This game auto saves, therefore you don't have to worry about it"
    }
}
