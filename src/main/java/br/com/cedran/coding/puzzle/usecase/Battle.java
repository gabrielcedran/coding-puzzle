package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;

public class Battle extends Scenario {

    public Battle(OutputGateway output, InputGateway input) {
        super(output, input);
    }

    @Override
    public Scenario start() {
        return null;
    }
}
