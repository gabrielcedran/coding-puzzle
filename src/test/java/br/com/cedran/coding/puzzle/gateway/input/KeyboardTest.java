package br.com.cedran.coding.puzzle.gateway.input;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
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
        when(scanner.next()).thenReturn("Hello");

        // WHEN the keyboard tries to read the input
        String text = keyboard.readString();

        // THEN the text returned is "Hello"
        assertThat("Text returned", text, equalTo("Hello"));
    }

    @Test
    public void readIntegerTest() {
        // GIVEN The user will type 1
        when(scanner.next()).thenReturn("1");

        // WHEN the keyboard tries to read the input
        Integer input = keyboard.readInteger();

        // THEN the text returned is "Hello"
        assertThat("Number returned", input, equalTo(1));
    }

    @Test
    public void readInvalidIntegerTest() {
        // GIVEN The user will type A
        when(scanner.next()).thenReturn("A");

        // WHEN the keyboard tries to read the input
        Integer input = keyboard.readInteger();

        // THEN no text is returned
        assertThat("Number returned", input, nullValue());
    }

    @Test
    public void waitAnyInputTest() throws IOException {
        // GIVEN The user type press any key
        InputStream printStream = Mockito.mock(InputStream.class);
        System.setIn(printStream);
        when(printStream.read()).thenReturn(1);

        // WHEN the keyboard tries to read the input
        keyboard.waitAnyInput();

        // THEN the processing finishes

    }

    @Test
    public void exceptionWhileWaitAnyInputTest() throws IOException {
        // GIVEN The user type press any key
        InputStream printStream = Mockito.mock(InputStream.class);
        System.setIn(printStream);
        when(printStream.read()).thenThrow(new IOException());

        // WHEN the keyboard tries to read the input
        keyboard.waitAnyInput();

        // THEN the processing finishes

    }
}
