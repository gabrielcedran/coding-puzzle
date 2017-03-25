package br.com.cedran.coding.puzzle.usecase

class ShowMenuSpec extends BaseSpec {

    ShowMenu showMenu

    def setup() {
        showMenu = new ShowMenu(screen, keyboard)
    }

    def "Test menu messages"() {
        given: "the show menu use case is instantiated and the user will select option 1 - "
        keyboard.readInteger() >> 1

        when: "show menu executes"
        showMenu.start()

        then: "message displayed are the welcome greeting"
        screenMessages[0] == "Welcome to the blind land!"
        and: "the option new game"
        screenMessages[1] == "1- New Game"
        and: "the option load game"
        screenMessages[2] == "2- Load Game"
        and: "the option exit"
        screenMessages[3] == "3- Exit"

    }

    def "Test new game flow"() {
        given: "the messages were displayed and the user will select the option 1 - New Game"
        keyboard.readInteger() >> 1

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the build profile screen is returned"
        useCase instanceof BuildProfile

    }

    def "Test quit flow"() {
        given: "the messages were displayed and the user will select the option 3 - Exit"
        keyboard.readInteger() >> 3

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the build profile screen is returned"
        useCase == null

    }

    def "Test invalid option"() {
        given: "the messages were displayed and the user will select the option -1"
        keyboard.readInteger() >> -1

        when: "show menu executes"
        Scenario useCase = showMenu.start()

        then: "the build profile screen is returned"
        useCase instanceof ShowMenu

    }
}