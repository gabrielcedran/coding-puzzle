package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.model.characters.Character
import br.com.cedran.coding.puzzle.model.characters.Warrior
import br.com.cedran.coding.puzzle.model.options.TextColors

class BuildCharacterSpec extends BaseSpec {

    BuildCharacter buildCharacter
    Character character

    def setup() {
        character = new Warrior()
        character.drawing = ["character drawing"]
        buildCharacter = new BuildCharacter(screen, keyboard, character)
    }

    def "Message displayed at the first step of this UC"() {
        given: "the character hasn't got a color yet"
        character.color = null
        and: "the use will select the option '1 - Red'"
        keyboard.readInteger() >> 1

        when: "the build character is started"
        buildCharacter.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the message 'Which color would you like your character to have?' is displayed"
        screenMessages[1] == "Which color would you like your character to have?"
    }

    def "Character color selection"() {
        given: "the character hasn't got a color yet"
        character.color = null
        and: "the use will select the option '1 - Red'"
        keyboard.readInteger() >> 1

        when: "the build character is started"
        Scenario useCase = buildCharacter.start()

        then: "the color red is set to the character"
        character.color == TextColors.RED
        and: "the uc build character is returned"
        useCase instanceof BuildCharacter
    }

    def "Character nickname definition"() {
        given: "the character has a color selected"
        character.color = TextColors.BLUE
        and: "the player will type the nickname 'Cedran'"
        keyboard.readString() >> "Cedran"

        when: "the build character is started"
        Scenario useCase = buildCharacter.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the messages 'The color of you character seems really good!' and 'What about giving it a nickname?' are displayed"
        screenMessages[1] == "The color of you character seems really good!"
        screenMessages[2] == "What about giving it a nickname?"
        and: "the nickname 'Cedran' is set to the character"
        character.name == "Cedran"
        and: "the uc build character is returned"
        useCase instanceof BuildCharacter
    }

    def "Fulfilled character"() {
        given: "the character has color and nickname"
        character.color = TextColors.YELLOW
        character.name = "Cedran"

        when: "the build character is started"
        Scenario useCase = buildCharacter.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the messages 'The color of you character seems really good!' and 'What about giving it a nickname?' are displayed"
        screenMessages[1] == "Hello brave warrior Cedran. Are you ready to start your journey?"
        and: "the UC Explore is returned"
        useCase instanceof Tutorial
    }
}
