package br.com.cedran.coding.puzzle.usecase

class ShowMenuSpec extends BaseSpec {

    ShowMenu showMenu

    def setup() {
        showMenu = new ShowMenu(screen, keyboard)
        showMenu.logo = ["Freak Island Logo"]
    }

    def "Test menu messages"() {
        given: "the show menu use case is instantiated and the user will select option 1 - New Game"
        keyboard.readInteger() >> 1

        when: "show menu executes"
        showMenu.start()

        then: "the Freak Island logo is displayed"
        screenMessages[0] == "Freak Island Logo"
        and: "a greeting message is displayed"
        screenMessages[1] == "The place where you meet creatures that you've never imagined before!"
        and: "the option new game is displayed"
        screenMessages[2] == "1- New Game"
        and: "the option load game is displayed"
        screenMessages[3] == "2- Load Game"
        and: "the option exit is displayed"
        screenMessages[4] == "3- Exit"

    }

    def "Test new game flow"() {
        given: "the messages were displayed and the user will select the option 1 - New Game"
        keyboard.readInteger() >> 1

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the build character scenario is returned"
        useCase instanceof BuildCharacter

    }

    def "Test load game flow"() {
        given: "the messages were displayed and the user will select the option 2 - Load Game"
        keyboard.readInteger() >> 2

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the load character scenario is returned"
        useCase instanceof LoadCharacter

    }

    def "Test quit flow"() {
        given: "the messages were displayed and the user will select the option 3 - Exit"
        keyboard.readInteger() >> 3

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the build character screen is returned"
        useCase == null

    }

    def "Test invalid option"() {
        given: "the messages were displayed and the user will select the option -1"
        keyboard.readInteger() >> -1

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the build character screen is returned"
        useCase instanceof ShowMenu

    }
}