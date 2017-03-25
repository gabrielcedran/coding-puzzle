package br.com.cedran.coding.puzzle.gateway.input;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class KeyboardTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private Keyboard keyboard;

    @Test
    public void readStringTest() {
        // GIVEN the user will type Hello
        Mockito.when(scanner.next()).thenReturn("Hello");

        // WHEN the keyboard tries to read the input
        String text = keyboard.readString();

        // THEN the text returned is "Hello"
        assertThat("Text returned", text, equalTo("Hello"));
    }

    @Test
    public void readIntegerTest() {
        // GIVEN The user will type 1
        Mockito.when(scanner.nextInt()).thenReturn(1);

        // WHEN the keyboard tries to read the input
        Integer input = keyboard.readInteger();

        // THEN the text returned is "Hello"
        assertThat("Number returned", input, equalTo(1));
    }

    @Test
    public void waitAnyInputTest() {
        // GIVEN The user type will press Enter
        Mockito.when(scanner.next()).thenReturn("\\n");

        // WHEN the keyboard tries to read the input
        keyboard.waitAnyInput();

        // THEN the processing finishes

    }
}
