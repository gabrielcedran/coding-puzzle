package br.com.cedran.coding.puzzle.gateway.input;

import java.util.Scanner;

import br.com.cedran.coding.puzzle.gateway.InputGateway;

public class Keyboard implements InputGateway {

    private Scanner scanner;

    public Keyboard(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readString() {
        return scanner.next();
    }

    @Override
    public Integer readInteger() {
        return scanner.nextInt();
    }

    @Override
    public void waitAnyInput() {
        scanner.next();
    }
}
