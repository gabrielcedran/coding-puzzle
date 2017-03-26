package br.com.cedran.coding.puzzle.gateway.database;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Scanner;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.cedran.coding.puzzle.model.characters.Character;
import br.com.cedran.coding.puzzle.model.characters.Warrior;
import br.com.cedran.coding.puzzle.model.options.TextColors;

@RunWith(MockitoJUnitRunner.class)
public class HardDiskTest {

    @Mock
    private Scanner scanner;

    @InjectMocks
    private HardDisk hardDisk;

    @Test
    public void testSaveAndLoadCharacter() {
        // GIVEN the following character
        Character character = new Warrior();
        character.setName("Cedran");
        character.setColor(TextColors.RED);
        character.setSteps(10l);
        character.increaseExperience(10l);

        // WHEN this character is sent to save
        hardDisk.saveCharacter(character);

        // THEN one saved character must exist
        Character savedCharacter = hardDisk.loadCharacter();
        assertThat("character must exists", savedCharacter, notNullValue());
        // AND this character has the same values of the original
        assertThat(savedCharacter.getName(), Matchers.equalTo(character.getName()));
        assertThat(savedCharacter.getColor(), Matchers.equalTo(character.getColor()));
        assertThat(savedCharacter.getSteps(), Matchers.equalTo(character.getSteps()));
        assertThat(savedCharacter.getExperience(), Matchers.equalTo(character.getExperience()));
    }

}
