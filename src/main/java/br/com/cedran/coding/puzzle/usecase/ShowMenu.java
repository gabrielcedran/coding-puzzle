package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.CharacterFactory;
import br.com.cedran.coding.puzzle.model.options.Menu;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class ShowMenu extends Scenario {

    public ShowMenu(OutputGateway output, InputGateway input) {
        super(output, input);
    }

    @Override
    public Scenario start() {
        output.clear();

        output.println(TextColors.RED, "Welcome to the blind land!");
        output.println(TextColors.GREEN, Menu.NEW_GAME.getNumber() + "- " + Menu.NEW_GAME.getDescription());
        output.println(Menu.LOAD_GAME.getNumber() + "- " + Menu.LOAD_GAME.getDescription());
        output.println(Menu.QUIT_GAME.getNumber() + "- " + Menu.QUIT_GAME.getDescription());

        return verifyOption(this.obtainInteger());
    }

    private Scenario verifyOption(Integer option) {
        Menu menu = Menu.getByNumber(option);
        if (Menu.NEW_GAME.equals(menu)) {
            return new BuildProfile(this.output, this.input, CharacterFactory.getInstance());
        } else if (Menu.LOAD_GAME.equals(menu)) {
            return null;
        } else if (Menu.QUIT_GAME.equals(menu)) {
            return null;
        }
        return new ShowMenu(this.output, this.input);
    }

}
