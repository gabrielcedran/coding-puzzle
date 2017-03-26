package br.com.cedran.coding.puzzle.gateway.input;

import java.io.IOException;
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
        Integer option = null;
        try {
            option = Integer.parseInt(scanner.next());
        } catch (Exception e) {

        }
        return option;
    }

    @Override
    public void waitAnyInput() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
