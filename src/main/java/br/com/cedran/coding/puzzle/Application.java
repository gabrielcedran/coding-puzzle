package br.com.cedran.coding.puzzle;

import java.util.Scanner;

import br.com.cedran.coding.puzzle.gateway.input.Keyboard;
import br.com.cedran.coding.puzzle.gateway.output.Screen;
import br.com.cedran.coding.puzzle.usecase.ShowMenu;
import br.com.cedran.coding.puzzle.usecase.UseCase;

public class Application {

    public static void main(String[] args) {
        UseCase nextUseCase = new ShowMenu(new Screen(), new Keyboard(new Scanner(System.in)));
        do {
            nextUseCase = nextUseCase.start();
        } while (nextUseCase != null);
    }

}
