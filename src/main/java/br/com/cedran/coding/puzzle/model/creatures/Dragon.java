package br.com.cedran.coding.puzzle.model.creatures;

import br.com.cedran.coding.puzzle.model.options.TextColors;

public class Dragon extends Monster {

    @Override
    public String getName() {
        return "Dragon";
    }

    @Override
    public String getIntroduction() {
        return "Wow, you just found a big dragon!";
    }

    @Override
    public String getNoise() {
        return "Rooooarrrr";
    }

    @Override
    public Long getLife() {
        return 100l;
    }

    @Override
    public Long getExperience() {
        return 4l;
    }

    @Override
    public String[] getDrawing() {
        return drawing;
    }

    @Override
    public TextColors getColor() {
        return TextColors.GREEN;
    }

    private String[] drawing = {
            // @formatter:off
            "              __                  __                ",
            "             ( _)                ( _)               ",
            "            / / \\              / /\\_\\_             ",
            "           / /   \\            / / | \\ \\            ",
            "          / /     \\          / /  |\\ \\ \\           ",
            "         /  /   ,  \\ ,       / /   /|  \\ \\          ",
            "        /  /    |\\_ /|      / /   / \\   \\_\\         ",
            "       /  /  |\\/ _ '_|\\    / /   /   \\    \\        ",
            "      |  /   |/  0 \\0\\ \\  / |    |    \\    \\       ",
            "      |    |\\|      \\_\\_ /  /    |     \\    \\      ",
            "      |  | |/    \\.\\ o\\o)  /      \\     |    \\     ",
            "      \\    |     /\\`v-v  /        |    |     \\    ",
            "       | \\/    /_| \\_|  /         |    | \\    \\   ",
            "       | |    /__/_     /   _____  |    |  \\    \\  ",
            "       \\|    [__]  \\_/  |_________  \\   |   \\    () ",
            "        /    [___] (    \\         \\  |\\ |   |   //  ",
            "       |    [___]                  |\\| \\|   /  |/   ",
            "      /|    [____]                  \\  |/\\ / / ||   ",
            "     (  \\   [____ /     ) _\\      \\  \\    \\| | ||   ",
            "      \\  \\  [_____|    / /     __/    \\   / / //    ",
            "      |   \\ [_____/   / /        \\    |   \\/ //     ",
            "      |   /  '----|   /=\\____   _/    |   / //      ",
            "   __ /  /        |  /   ___/  _/\\    \\  | ||       ",
            "  (/-(/-\\)       /   \\  (/\\/\\)/  |    /  | /        ",
            "                (/\\/\\)           /   /   //         ",
            "                       _________/   /    /          ",
            "                      \\____________/    (           "

            // @formatter:on
    };
}
