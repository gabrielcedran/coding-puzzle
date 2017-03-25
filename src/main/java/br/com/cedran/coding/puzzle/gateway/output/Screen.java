package br.com.cedran.coding.puzzle.gateway.output;

import java.util.Arrays;
import java.util.Optional;

import br.com.cedran.coding.puzzle.gateway.OutputGateway;
import br.com.cedran.coding.puzzle.model.options.TextColors;

public class Screen implements OutputGateway {

    public void println(TextColors color, String text) {
        println(color.getAscii() + text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void print(String text) {
        System.out.print(text);
    }

    public void print(TextColors color) {
        System.out.print(color.getAscii());
    }

    public void print(TextColors color, String... texts) {
        this.print(Optional.ofNullable(color).orElse(TextColors.RESET));
        Arrays.stream(texts).forEach(line -> this.println(line));
        this.print(TextColors.RESET);
    }

    public void clear() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
    }

}
