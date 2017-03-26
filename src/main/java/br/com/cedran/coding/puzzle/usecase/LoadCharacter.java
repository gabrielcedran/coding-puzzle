package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.gateway.SaveGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class LoadCharacter extends Scenario {

    private SaveGateway saveGateway;

    public LoadCharacter(OutputGateway output, InputGateway input, SaveGateway saveGateway) {
        super(output, input);
        this.saveGateway = saveGateway;
    }

    @Override
    public Scenario execute() {

        Scenario nextScenario = null;

        Character character = this.saveGateway.loadCharacter();
        if (character == null) {
            output.println(TextColors.RED, "Couldn't find any previously saved game.");
            output.println(TextColors.RED, "Press ENTER to go back to the menu...");
            nextScenario = new ShowMenu(output, input);
        } else {
            output.println("Welcome back " + character.getName());
            output.println(TextColors.RED, "Press ENTER start playing again...");
            if (character.getSteps() <= 10) {
                nextScenario = new Tutorial(output, input, character);
            } else {
                nextScenario = new Explore(output, input, character, null, new Random(), saveGateway);
            }
        }

        input.waitAnyInput();
        return nextScenario;
    }
}
