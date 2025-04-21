package br.com.monicapariz.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found");
    }

}
