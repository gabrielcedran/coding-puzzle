package br.com.cedran.coding.puzzle.usecase;

import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;
import br.com.cedran.coding.puzzle.model.creatures.MonsterFactory;
import br.com.cedran.coding.puzzle.model.options.Actions;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class Battle extends Scenario {

    private Monster monster;

    private MonsterFactory monsterFactory;

    private Random random;

    private Actions action;

    private Character character;

    public Battle(OutputGateway output, InputGateway input, Random random, Character character, MonsterFactory monsterFactory) {
        super(output, input);
        this.random = random;
        this.character = character;
        this.monsterFactory = monsterFactory;
    }

    @Override
    public Scenario start() {

        output.clear();

        handleMonster();

        Scenario nextScenario = calculateDamage();

        printLifeRemaining();

        if (nextScenario instanceof Battle) {
            output.println("Type " + Actions.ATTACK.getKey() + " and ENTER to " + Actions.ATTACK.getDescription());
            verifyOption(input.readString());
        } else {
            output.println("Press ENTER to continue...");
            input.readString();
        }
        return nextScenario;
    }

    private void handleMonster() {
        if (monster == null) {
            monster = monsterFactory.getMonster();
            output.println(monster.getIntroduction());
        }
        output.print(monster.getColor(), monster.getDrawing());
        if (random.nextInt(4) == 1) {
            output.println(monster.getNoise());
        }
    }

    private void printLifeRemaining() {
        output.println(monster.getName() + "'s Life [" + monster.getLifeRemaining() + "/" + monster.getLife() + "]");

        output.print("[");
        Double percentage = monster.getLifeRemaining().doubleValue() / monster.getLife().doubleValue();
        Long filledCols = Math.round(percentage * 20);
        Long emptyCols = 20 - filledCols;
        if (percentage < 0.3) {
            output.print(TextColors.RED);
        } else if (percentage >= 0.3 && percentage < 0.6) {
            output.print(TextColors.YELLOW);
        } else {
            output.print(TextColors.GREEN);
        }
        for (int i = 0; i < filledCols; i++) {
            output.print("#");
        }
        for (int i = 0; i < emptyCols; i++) {
            output.print(" ");
        }
        output.print(TextColors.RESET);
        output.println("]");
    }

    private Scenario calculateDamage() {
        if (action != null) {
            Integer damage = random.nextInt(10);
            output.println("You cause a damage of " + damage + " points!");
            monster.decreaseLifeRemaining(damage);
            if (monster.getLifeRemaining() <= 0) {
                return new EndBattle(this.output, this.input, this.character, this.monster);
            }

        }
        return this;
    }

    private void verifyOption(String option) {
        action = Actions.getByKey(option);
    }
}
