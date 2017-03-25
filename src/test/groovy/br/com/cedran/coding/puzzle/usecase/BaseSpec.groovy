package br.com.cedran.coding.puzzle.usecase

import br.com.cedran.coding.puzzle.gateway.input.Keyboard
import br.com.cedran.coding.puzzle.gateway.output.Screen
import spock.lang.Specification

class BaseSpec extends Specification {

    Screen screen
    Keyboard keyboard
    def screenMessages = []

    def setup() {
        screen = Mock(Screen)
        keyboard = Mock(Keyboard)
        screen.println(_, _) >> { args -> screenMessages.add(args[1]) }
        screen.println(_) >> { args -> screenMessages.add(args[0]) }
        screen.print(_ as String) >> { args -> screenMessages.add(args[0]) }
        screen.print(_, _) >> { args -> screenMessages.addAll(args[1]) }
    }

    def cleanup() {
        screenMessages.clear()
    }
}
