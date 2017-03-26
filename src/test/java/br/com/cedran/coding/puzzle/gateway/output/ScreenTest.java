package br.com.cedran.coding.puzzle.gateway.output;

import java.io.IOException;
import java.io.PrintStream;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cedran.coding.puzzle.model.options.TextColors;

@RunWith(MockitoJUnitRunner.class)
public class ScreenTest {

    @Mock
    private PrintStream printStream;

    private Screen screen;

    private ArgumentCaptor<String> argumentNewLine;

    private ArgumentCaptor<String> argument;

    @Before
    public void setup() {
        System.setOut(printStream);
        argumentNewLine = ArgumentCaptor.forClass(String.class);
        argument = ArgumentCaptor.forClass(String.class);
        Mockito.doNothing().when(printStream).println(argumentNewLine.capture());
        Mockito.doNothing().when(printStream).print(argument.capture());

        screen = new Screen();
    }

    @Test
    public void testPrintTextColorAndText() throws IOException {
        // GIVEN the text "Welcome to the freak land!"
        String message = "Welcome to the freak land!";
        // AND the color yellow
        TextColors color = TextColors.YELLOW;

        // WHEN I send them to be printed
        screen.println(color, message);

        // THEN a line beginning with the ascii code of the color yellow followed by the text is printed
        Assert.assertThat(argumentNewLine.getValue(), Matchers.equalTo(TextColors.YELLOW.getAscii() + "Welcome to the freak land!"));

    }

    @Test
    public void testPrintTextInNewLine() throws IOException {
        // GIVEN the text "Welcome to the freak land!"
        String message = "Welcome to the freak land!";

        // WHEN I send it to be printed
        screen.println(message);

        // THEN a line containing the text is printed
        Assert.assertThat(argumentNewLine.getValue(), Matchers.equalTo("Welcome to the freak land!"));

    }

    @Test
    public void testPrintText() throws IOException {
        // GIVEN the text "Welcome to the freak land!"
        String message = "Welcome to the freak land!";

        // WHEN I send it to be printed
        screen.print(message);

        // THEN a line containing the text is printed
        Assert.assertThat(argument.getValue(), Matchers.equalTo("Welcome to the freak land!"));

    }

    @Test
    public void testPrintColor() throws IOException {
        // GIVEN the text color GREEN
        TextColors textColor = TextColors.GREEN;

        // WHEN I send it to be printed
        screen.print(textColor);

        // THEN a line containing the ascii of that color is printed
        Assert.assertThat(argument.getValue(), Matchers.equalTo(textColor.getAscii()));

    }

    @Test
    public void testPrintColorAndMultipleLines() throws IOException {
        // GIVEN the text color GREEN
        TextColors textColor = TextColors.WHITE;
        // AND the words "Welcome", "to", "the", "freak" and "land!"
        String[] texts = { "Welcome", "to", "the", "freak", "land!" };

        // WHEN I send them to be printed
        screen.print(textColor, texts);

        // THEN lines containing the colors are printed
        Assert.assertThat(argument.getAllValues(), Matchers.containsInRelativeOrder(textColor.getAscii(), TextColors.RESET.getAscii()));
        // AND lines containing the texts are printed as well
        Assert.assertThat(argumentNewLine.getAllValues(), Matchers.containsInRelativeOrder("Welcome", "to", "the", "freak", "land!"));

    }

    @Test
    public void testClearScreen() throws IOException {
        // GIVEN the a screen full of text

        // WHEN I ask it to be clean
        screen.clear();

        // THEN lines containing the commands to clear the screen are printed
        Assert.assertThat(argument.getValue(), Matchers.equalTo("\u001b[2J\u001b[H"));

    }
}
