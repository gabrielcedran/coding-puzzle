package br.com.cedran.coding.puzzle.model.characters;

public class Warrior extends Character {

    private static final long serialVersionUID = 1L;

    // @formatter:off
    private String[] drawing = new String[] {
            "                   _.--.    .--._                   ",
            "                 .\"  .\"      \".  \".                 ",
            "                ;  .\"    /\\    \".  ;                ",
            "                ;  '._,-/  \\-,_.`  ;                ",
            "                \\  ,`  / /\\ \\  `,  /                ",
            "                 \\/    \\/  \\/    \\/                 ",
            "                 ,=_    \\/\\/    _=,                 ",
            "                 |  \"_   \\/   _\"  |                 ",
            "                 |_   '\"-..-\"'   _|                 ",
            "                 | \"-.        .-\" |                 ",
            "                 |    \"\\    /\"    |                 ",
            "                 |      |  |      |                 ",
            "         ___     |      |  |      |     ___         ",
            "     _,-\",  \",   '_     |  |     _'   ,\"  ,\"-,_     ",
            "   _(  \\  \\   \\\"=--\"-.  |  |  .-\"--=\"/   /  /  )_   ",
            " ,\"  \\  \\  \\   \\      \"-'--'-\"      /   /  /  /  \". ",
            "!     \\  \\  \\   \\                  /   /  /  /     !",
            ":      \\  \\  \\   \\                /   /  /  /      :"
    };
    // @formatter:on

    @Override
    public String[] getDrawing() {
        return drawing;
    }

}
