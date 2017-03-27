package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public abstract class Scenario {

    protected OutputGateway output;
    protected InputGateway input;

    public Scenario(OutputGateway output, InputGateway input) {
        this.output = output;
        this.input = input;
    }

    public Scenario start() {
        output.clear();
        output.print(TextColors.RESET);
        return this.execute();
    }

    public abstract Scenario execute();

    Integer obtainInteger() {
        output.print("Type one option: ");
        return input.readInteger();
    }

    String obtainString() {
        return input.readString();
    }

    void waitAnyInput() {
        input.waitAnyInput();
    }

}
