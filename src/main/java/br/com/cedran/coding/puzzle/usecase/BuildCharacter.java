package br.com.cedran.coding.puzzle.usecase;

import java.util.Optional;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class BuildCharacter extends Scenario {

    private Character character;

    public BuildCharacter(OutputGateway output, InputGateway input, Character character) {
        super(output, input);
        this.character = character;
    }

    @Override
    public Scenario execute() {

        output.print(character.getColor(), character.getDrawing());

        Scenario nextScenario = this;
        if (character.getColor() == null) {

            output.println("Which color would you like your character to have?");
            output.println(TextColors.RED, TextColors.RED.getNumber() + " - Red");
            output.println(TextColors.GREEN, TextColors.GREEN.getNumber() + " - Green");
            output.println(TextColors.BLUE, TextColors.BLUE.getNumber() + " - Blue");
            output.println(TextColors.WHITE, TextColors.WHITE.getNumber() + " - White");
            output.println(TextColors.YELLOW, TextColors.YELLOW.getNumber() + " - Yellow");

            output.print(TextColors.RESET);
            verifyOption(Optional.ofNullable(this.obtainInteger()).orElse(-1));
        } else if (character.getName() == null) {
            output.println("The color of you character seems really good!");
            output.println("What about giving it a nickname?");
            verifyName(this.obtainString());
        } else {
            output.println("Hello brave warrior " + character.getName() + ". Are you ready to start your journey?");
            this.waitAnyInput();
            nextScenario = new Tutorial(this.output, this.input, this.character);
        }

        return nextScenario;
    }

    private void verifyOption(Integer option) {
        if (option > 0 && option < 6) {
            character.setColor(TextColors.getByNumber(option));
        }
    }

    private void verifyName(String name) {
        if (name != null && !name.trim().equals("")) {
            character.setName(name.trim());
        }
    }
}
