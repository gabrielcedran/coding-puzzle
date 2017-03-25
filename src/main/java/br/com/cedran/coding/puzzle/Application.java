package br.com.cedran.coding.puzzle;

import java.util.Scanner;

import br.com.cedran.coding.puzzle.gateway.input.Keyboard;
import br.com.cedran.coding.puzzle.gateway.output.Screen;
import br.com.cedran.coding.puzzle.usecase.Scenario;
import br.com.cedran.coding.puzzle.usecase.ShowMenu;

public class Application {

    public static void main(String[] args) {
        Scenario nextScenario = new ShowMenu(new Screen(), new Keyboard(new Scanner(System.in)));
        do {
            nextScenario = nextScenario.start();
        } while (nextScenario != null);
    }

}
