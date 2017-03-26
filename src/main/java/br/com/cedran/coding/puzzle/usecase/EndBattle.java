package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.gateway.SaveGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class EndBattle extends Scenario {

    private Monster monster;

    private Character character;

    private SaveGateway saveGateway;

    public EndBattle(OutputGateway output, InputGateway input, Character character, Monster monster, SaveGateway saveGateway) {
        super(output, input);
        this.character = character;
        this.monster = monster;
        this.saveGateway = saveGateway;
    }

    @Override
    public Scenario execute() {
        character.increaseExperience(monster.getExperience());
        saveGateway.saveCharacter(character);

        output.print(TextColors.YELLOW, message);
        output.println("You just defeated a " + monster.getName() + " and earned " + monster.getExperience() + " points!");
        output.println("Press any key to start exploring again...");
        input.waitAnyInput();

        return new Explore(this.output, this.input, this.character, null, new Random(), this.saveGateway);
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
