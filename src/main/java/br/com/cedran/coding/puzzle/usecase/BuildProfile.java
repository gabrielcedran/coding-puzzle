package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class BuildProfile extends Scenario {

    private Character character;

    public BuildProfile(OutputGateway output, InputGateway input, Character character) {
        super(output, input);
        this.character = character;
    }

    @Override
    public Scenario start() {
        output.clear();
        output.print(TextColors.RESET);

        output.print(character.getColor(), character.getDrawing());

        Scenario nextScenario = new BuildProfile(this.output, this.input, this.character);
        if (character.getColor() == null) {

            output.println("Which color would you like your character to have?");
            output.println(TextColors.RED, TextColors.RED.getNumber() + " - Red");
            output.println(TextColors.GREEN, TextColors.GREEN.getNumber() + " - Green");
            output.println(TextColors.BLUE, TextColors.BLUE.getNumber() + " - Blue");
            output.println(TextColors.WHITE, TextColors.WHITE.getNumber() + " - White");
            output.println(TextColors.YELLOW, TextColors.YELLOW.getNumber() + " - Yellow");

            output.print(TextColors.RESET);
            verifyOption(this.obtainInteger());
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
