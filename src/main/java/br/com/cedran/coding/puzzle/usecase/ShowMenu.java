package br.com.cedran.coding.puzzle.usecase;

import java.util.Optional;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.gateway.database.HardDisk;
import br.com.cedran.coding.puzzle.model.characters.CharacterFactory;
import br.com.cedran.coding.puzzle.model.options.Menu;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class ShowMenu extends Scenario {

    public ShowMenu(OutputGateway output, InputGateway input) {
        super(output, input);
    }

    @Override
    public Scenario execute() {
        output.print(TextColors.GREEN, logo);
        output.println(TextColors.RED, "The place where you meet creatures that you've never imagined before!");
        output.println(TextColors.GREEN, Menu.NEW_GAME.getNumber() + "- " + Menu.NEW_GAME.getDescription());
        output.println(Menu.LOAD_GAME.getNumber() + "- " + Menu.LOAD_GAME.getDescription());
        output.println(Menu.QUIT_GAME.getNumber() + "- " + Menu.QUIT_GAME.getDescription());

        return verifyOption(this.obtainInteger());
    }

    private Scenario verifyOption(Integer option) {
        Menu menu = Menu.getByNumber(Optional.ofNullable(option).orElse(-1));
        if (Menu.NEW_GAME.equals(menu)) {
            return new BuildCharacter(this.output, this.input, new CharacterFactory().getCharacter("WARRIOR"));
        } else if (Menu.LOAD_GAME.equals(menu)) {
            return new LoadCharacter(this.output, this.input, new HardDisk());
        } else if (Menu.QUIT_GAME.equals(menu)) {
            return null;
        }
        return this;
    }

    private String[] logo = {
            // @formatter:off
            "  ______              _      _____     _                 _  ",
            " |  ____|            | |    |_   _|   | |               | | ",
            " | |__ _ __ ___  __ _| | __   | |  ___| | __ _ _ __   __| | ",
            " |  __| '__/ _ \\/ _` | |/ /   | | / __| |/ _` | '_ \\ / _` | ",
            " | |  | | |  __/ (_| |   <   _| |_\\__ \\ | (_| | | | | (_| | ",
            " |_|  |_|  \\___|\\__,_|_|\\_\\ |_____|___/_|\\__,_|_| |_|\\__,_| "
            // @formatter:on
    };

}
