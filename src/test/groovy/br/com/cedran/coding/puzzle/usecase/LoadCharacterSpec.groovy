package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.gateway.SaveGateway
import br.com.cedran.coding.puzzle.model.characters.Character

class LoadCharacterSpec extends BaseSpec {

    LoadCharacter loadCharacter
    SaveGateway saveGateway

    def setup() {
        saveGateway = Mock(SaveGateway)
        loadCharacter = new LoadCharacter(screen, keyboard, saveGateway)
    }

    def "Load character containing more than 10 steps"() {
        given: "there is a saved character"
        Character character = Mock(Character)
        saveGateway.loadCharacter() >> character
        and: "this character has the name Cedran and has more than 10 steps"
        character.getName() >> "Cedran"
        character.getSteps() >> 11l

        when: "the load character executes"
        Scenario scenario = loadCharacter.start()

        then: "the greeting message is displayed"
        screenMessages[0] == "Welcome back Cedran"
        and: "a message saying to press ENTER is displayed"
        screenMessages[1] == "Press ENTER start playing again..."
        and: "the next scenario returned is Explore"
        scenario instanceof Explore

    }

    def "Load character containing 10 steps"() {
        given: "there is a saved character"
        Character character = Mock(Character)
        saveGateway.loadCharacter() >> character
        and: "this character has the name Cedran and has 10 steps"
        character.getName() >> "Cedran"
        character.getSteps() >> 10l

        when: "the load character executes"
        Scenario scenario = loadCharacter.start()

        then: "the greeting message is displayed"
        screenMessages[0] == "Welcome back Cedran"
        and: "a message saying to press ENTER is displayed"
        screenMessages[1] == "Press ENTER start playing again..."
        and: "the next scenario returned is Explore"
        scenario instanceof Tutorial

    }

    def "Try to load inexistent character"() {
        given: "there isn't a saved character"
        saveGateway.loadCharacter() >> null

        when: "the load character executes"
        Scenario scenario = loadCharacter.start()

        then: "an error message is displayed"
        screenMessages[0] == "Couldn't find any previously saved game."
        and: "a message saying to press ENTER is displayed"
        screenMessages[1] == "Press ENTER to go back to the menu..."
        and: "the next scenario returned is Show Menu"
        scenario instanceof ShowMenu

    }
}
