package br.com.cedran.coding.puzzle.gateway;

public interface InputGateway {

    String readString();

    Integer readInteger();

    void waitAnyInput();
}
