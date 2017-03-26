package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.model.characters.Character
import br.com.cedran.coding.puzzle.model.characters.Warrior
import br.com.cedran.coding.puzzle.model.options.TextColors

class BuildProfileSpec extends BaseSpec {

    BuildProfile buildProfile
    Character character

    def setup() {
        character = new Warrior()
        character.drawing = ["character drawing"]
        buildProfile = new BuildProfile(screen, keyboard, character)
    }

    def "Message displayed at the first step of this UC"() {
        given: "the profile hasn't got a color yet"
        character.color = null
        and: "the use will select the option '1 - Red'"
        keyboard.readInteger() >> 1

        when: "the build profile is started"
        buildProfile.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the message 'Which color would you like your character to have?' is displayed"
        screenMessages[1] == "Which color would you like your character to have?"
    }

    def "Character color selection"() {
        given: "the profile hasn't got a color yet"
        character.color = null
        and: "the use will select the option '1 - Red'"
        keyboard.readInteger() >> 1

        when: "the build profile is started"
        Scenario useCase = buildProfile.start()

        then: "the color red is set to the character"
        character.color == TextColors.RED
        and: "the uc build profile is returned"
        useCase instanceof BuildProfile
    }

    def "Character nickname definition"() {
        given: "the profile a color selected"
        character.color = TextColors.BLUE
        and: "the use will type the nickname 'Cedran'"
        keyboard.readString() >> "Cedran"

        when: "the build profile is started"
        Scenario useCase = buildProfile.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the messages 'The color of you character seems really good!' and 'What about giving it a nickname?' are displayed"
        screenMessages[1] == "The color of you character seems really good!"
        screenMessages[2] == "What about giving it a nickname?"
        and: "the nickname 'Cedran' is set to the character"
        character.name == "Cedran"
        and: "the uc BuildProfile is returned"
        useCase instanceof BuildProfile
    }

    def "Fulfilled character"() {
        given: "the profile has color and nickname"
        character.color = TextColors.YELLOW
        character.name = "Cedran"

        when: "the build profile is started"
        Scenario useCase = buildProfile.start()

        then: "the character drawing is displayed"
        screenMessages[0] == "character drawing"
        and: "the messages 'The color of you character seems really good!' and 'What about giving it a nickname?' are displayed"
        screenMessages[1] == "Hello brave warrior Cedran. Are you ready to start your journey?"
        and: "the UC Explore is returned"
        useCase instanceof Tutorial
    }
}
