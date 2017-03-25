package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.MonsterFactory;
import br.com.cedran.coding.puzzle.model.options.Movements;

public class ExploreScenario extends Scenario {

    private Character character;
    private Movements lastMovement;
    private Random random;

    public ExploreScenario(OutputGateway output, InputGateway input, Character character, Movements lastMovement, Random random) {
        super(output, input);
        this.character = character;
        this.lastMovement = lastMovement;
        this.random = random;
    }

    @Override
    public Scenario start() {
        output.clear();
        output.print(character.getColor(), character.getDrawing());
        if (lastMovement != null) {
            output.println("Number of steps: " + character.getSteps() + " | Experience: " + character.getExperience() + " | Moving " + lastMovement.getDescription());
        } else {
            output.println("Number of steps: " + character.getSteps() + " | Experience: " + character.getExperience());
        }
        String option = this.obtainString();
        return verifyOption(option);
    }

    private Scenario verifyOption(String option) {
        Movements movement = Movements.getByKey(option);
        if (movement != null) {
            character.addStep();
            if (random.nextInt(9) == 1) {
                return new Battle(this.output, this.input, this.random, this.character, new MonsterFactory());
            } else {
                return new ExploreScenario(this.output, this.input, this.character, movement, this.random);
            }
        } else if ("Q".equals(option.toUpperCase())) {
            return new ShowMenu(this.output, this.input);
        }
        return new ExploreScenario(this.output, this.input, this.character, null, this.random);
    }

}
