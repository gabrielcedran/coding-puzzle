package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class EndBattle extends Scenario {

    private Monster monster;

    private Character character;

    public EndBattle(OutputGateway output, InputGateway input, Character character, Monster monster) {
        super(output, input);
        this.character = character;
        this.monster = monster;
    }

    @Override
    public Scenario start() {
        output.clear();
        character.increaseExperience(monster.getExperience());
        output.print(TextColors.YELLOW, message);
        output.println("You just defeated a " + monster.getName() + " and earned " + monster.getExperience());
        output.println("Press any key to start exploring again...");
        input.waitAnyInput();

        return new ExploreScenario(this.output, this.input, this.character, null, new Random());
    }

    private String[] message = {
            // @formatter:off
            "                                  _         ",
            "                                 | |        ",
            "   ___ ___  _ __   __ _ _ __ __ _| |_ ___   ",
            "  / __/ _ \\| '_ \\ / _` | '__/ _` | __/ __|  ",
            " | (_| (_) | | | | (_| | | | (_| | |_\\__ \\  ",
            "  \\___\\___/|_| |_|\\__, |_|  \\__,_|\\__|___/  ",
            "                   __/ |                    ",
            "                  |___/                     "
            // @formatter:on
    };
}
