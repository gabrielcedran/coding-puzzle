package br.com.cedran.coding.puzzle.usecase.battle;

import java.util.Arrays;
import java.util.Random;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.gateway.database.HardDisk;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.creatures.Monster;
import br.com.cedran.coding.puzzle.model.creatures.MonsterFactory;
import br.com.cedran.coding.puzzle.model.options.Actions;
import br.com.cedran.coding.puzzle.model.options.TextColors;
import br.com.cedran.coding.puzzle.usecase.Scenario;

public class Battle extends Scenario {

    private Monster monster;

    private MonsterFactory monsterFactory;

    private Random random;

    private Actions action;

    private Character character;

    private DamageEngine damageEngine;

    public Battle(OutputGateway output, InputGateway input, Random random, Character character, MonsterFactory monsterFactory) {
        super(output, input);
        this.random = random;
        this.character = character;
        this.monsterFactory = monsterFactory;
    }

    @Override
    public Scenario execute() {

        handleMonster();

        Scenario nextScenario = handleDamage();

        printLifeRemaining();

        if (nextScenario instanceof Battle) {
            output.print("Type: ");
            Arrays.stream(Actions.values()).forEach((action) -> output.print(action.getKey() + " to " + action.getDescription() + " | "));
            verifyOption(input.readString());
        } else {
            output.println("Press ENTER to continue...");
            input.waitAnyInput();
        }
        return nextScenario;
    }

    private void handleMonster() {
        if (monster == null) {
            monster = monsterFactory.getMonster();
            output.println(monster.getIntroduction());
            damageEngine = new DamageEngineFactory(this.character, this.monster, this.random, this.output).getInstance(this.monster);
        }
        output.print(monster.getColor(), monster.getDrawing());
        if (random.nextInt(4) == 1) {
            output.println(monster.getNoise());
        }
    }

    private void printLifeRemaining() {
        output.println(monster.getName() + "'s Life [" + monster.getLifeRemaining() + "/" + monster.getLife() + "]");
        printLifeBar();
    }

    private void printLifeBar() {
        Double percentage = monster.getLifeRemaining().doubleValue() / monster.getLife().doubleValue();
        Long filledCols = Math.round(percentage * 20);
        Long emptyCols = 20 - filledCols;

        defineBarColor(percentage);

        output.print("[");
        for (int i = 0; i < filledCols; i++) {
            output.print("#");
        }
        for (int i = 0; i < emptyCols; i++) {
            output.print(" ");
        }
        output.println("]");
        output.print(TextColors.RESET);
    }

    private void defineBarColor(Double percentage) {
        if (percentage < 0.2) {
            output.print(TextColors.RED);
        } else if (percentage >= 0.2 && percentage < 0.6) {
            output.print(TextColors.YELLOW);
        } else {
            output.print(TextColors.GREEN);
        }
    }

    private Scenario handleDamage() {
        Scenario nextScenario = this;

        damageEngine.execute(action);

        if (monster.getLifeRemaining() <= 0) {
            nextScenario = new EndBattle(this.output, this.input, this.character, this.monster, new HardDisk());
        }

        return nextScenario;
    }

    private void verifyOption(String option) {
        action = Actions.getByKey(option);
    }
}
