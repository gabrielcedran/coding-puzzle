package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.gateway.SaveGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.MonsterFactory;
import br.com.cedran.coding.puzzle.model.options.Movements;
import br.com.cedran.coding.puzzle.model.options.TextColors;
import br.com.cedran.coding.puzzle.usecase.battle.Battle;

public class Explore extends Scenario {

    private Character character;
    private Movements lastMovement;
    private Random random;
    private SaveGateway saveGateway;

    public Explore(OutputGateway output, InputGateway input, Character character, Movements lastMovement, Random random, SaveGateway saveGateway) {
        super(output, input);
        this.character = character;
        this.lastMovement = lastMovement;
        this.random = random;
        this.saveGateway = saveGateway;
    }

    @Override
    public Scenario execute() {

        output.print(character.getColor(), character.getDrawing());
        if (lastMovement != null) {
            output.println("Number of steps: " + character.getSteps() + " | Experience: " + character.getExperience() + " | Moving " + lastMovement.getDescription());
        } else {
            output.println("Number of steps: " + character.getSteps() + " | Experience: " + character.getExperience());
        }
        output.println(TextColors.YELLOW, Movements.FORWARD.getKey() + " - forward | " + Movements.BACKWARD.getKey() + " - backward | " + Movements.LEFT.getKey() + " -  left | "
                        + Movements.RIGHT.getKey() + " - right | Q - Menu");
        String option = this.obtainString();
        return verifyOption(option);
    }

    private Scenario verifyOption(String option) {
        Scenario nextScenario = null;

        Movements movement = Movements.getByKey(option);
        if (movement != null) {
            character.addStep();
            saveGateway.saveCharacter(character);
            if (random.nextInt(9) == 1) {
                nextScenario = new Battle(this.output, this.input, this.random, this.character, new MonsterFactory(this.random));
            } else {
                nextScenario = this;
                this.lastMovement = movement;
            }
        } else if ("Q".equals(option.toUpperCase())) {
            nextScenario = new ShowMenu(this.output, this.input);
        } else {
            nextScenario = this;
            this.lastMovement = null;
        }
        return nextScenario;
    }

}
