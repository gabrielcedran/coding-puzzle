package br.com.cedran.coding.puzzle.gateway.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Optional;

import br.com.cedran.coding.puzzle.gateway.SaveGateway;
import br.com.cedran.coding.puzzle.model.characters.Character;

public class HardDisk implements SaveGateway {
    @Override
    public void saveCharacter(Character character) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("character.saved");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(character);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Character loadCharacter() {
        Character character = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("character.saved");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            character = Optional.ofNullable(objectInputStream.readObject()).map(object -> (Character) object).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return character;
    }
}
