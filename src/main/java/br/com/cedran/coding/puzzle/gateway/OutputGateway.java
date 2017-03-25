package br.com.cedran.coding.puzzle.gateway;

import br.com.cedran.coding.puzzle.model.options.TextColors;

public interface OutputGateway {
    void println(TextColors color, String text);

    void println(String text);

    void print(String text);

    void print(TextColors color);

    void print(TextColors color, String... texts);

    void clear();
}
