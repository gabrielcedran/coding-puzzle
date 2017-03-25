package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.gateway.output.Screen
import spock.lang.Specification

class BaseSpec extends Specification {

    Screen screen
    def screenMessages = []

    def setup() {
        screen = Mock(Screen)
        screen.println(_, _) >> { args -> screenMessages.add(args[1]) }
        screen.println(_) >> { args -> screenMessages.add(args[0]) }
        screen.print(String) >> { args -> screenMessages.add(args[0]) }
    }

    def cleanup() {
        screenMessages.clear()
    }
}
