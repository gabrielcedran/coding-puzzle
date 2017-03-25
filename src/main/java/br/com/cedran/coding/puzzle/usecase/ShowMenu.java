package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.CharacterFactory;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class ShowMenu extends UseCase {

    public ShowMenu(OutputGateway output, InputGateway input) {
        super(output, input);
    }

    @Override
    public UseCase start() {
        output.clear();

        output.println(TextColors.RED, "Welcome to the blind land!");
        output.println(TextColors.GREEN, "1- New Game");
        output.println("2- Load Game");
        output.println("3- Exit");

        return verifyOption(this.obtainInteger());
    }

    private UseCase verifyOption(Integer option) {
        if (option.equals(1)) {
            return new BuildProfile(this.output, this.input, CharacterFactory.getInstance());
        } else if (option.equals(2)) {
            return null;
        } else if (option.equals(3)) {
            return null;
        }
        return new ShowMenu(this.output, this.input);
    }

}
