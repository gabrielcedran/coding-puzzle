package br.com.cedran.coding.puzzle.usecase;

import br.com.cedran.coding.puzzle.gateway.InputGateway;
import br.com.cedran.coding.puzzle.gateway.OutputGateway;

public abstract class UseCase {

    OutputGateway output;
    InputGateway input;

    UseCase(OutputGateway output, InputGateway input) {
        this.output = output;
        this.input = input;
    }

    public abstract UseCase start();

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
