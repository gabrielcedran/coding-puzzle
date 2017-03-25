package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class ExploreScenario extends UseCase {

    private Character character;

    public ExploreScenario(OutputGateway output, InputGateway input, Character character) {
        super(output, input);
        this.character = character;
    }

    @Override
    public UseCase start() {
        output.clear();
        output.print(TextColors.RESET);

        output.print(character.getColor(), character.getDrawing());
        this.waitAnyInput();

        return new ExploreScenario(this.output, this.input, this.character);
    }

}
