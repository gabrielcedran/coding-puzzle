package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.options.Actions;
import br.com.cedran.coding.puzzle.model.options.Movements;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class Tutorial extends Scenario {

    private Character character;

    public Tutorial(OutputGateway output, InputGateway input, Character character) {
        super(output, input);
        this.character = character;
    }

    @Override
    public Scenario start() {
        output.clear();
        output.print(TextColors.RESET);

        output.print(character.getColor(), character.getDrawing());
        if (this.character.getSteps() == 0) {
            output.println("Before you start, here are some tips:");
        } else {
            output.println("It seems that you haven't played much yet, therefore, lets remember how to play:");
        }

        output.println("To walk around use following keys:");
        output.println(TextColors.YELLOW, Movements.FORWARD.getKey() + " - to go forward");
        output.println(TextColors.YELLOW, Movements.BACKWARD.getKey() + " - to go backward");
        output.println(TextColors.GREEN, Movements.LEFT.getKey() + " - to go to the left");
        output.println(TextColors.GREEN, Movements.RIGHT.getKey() + " - to go to the right");
        output.println(TextColors.BLUE, "If you find any monster use " + Actions.ATTACK.getKey() + " to attack");
        output.println("If you want to go back to menu press Q");
        output.println("After selecting a movement, press ENTER");
        output.println(TextColors.RED, "If you type more than one option, that movement will be discarded");

        input.waitAnyInput();
        return new Explore(this.output, this.input, this.character, null, new Random());
    }
}
