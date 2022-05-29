package br.cursojava.exception;

public class MenssageNotFoundException extends Exception{

    private static final Long serialVersionUID = 1L;

    public MenssageNotFoundException(String message){
        super(message);
    }
    
}
