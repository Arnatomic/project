/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.exceptions;

/**
 *
 * @author Mr. Robot
 */
public class PersonaException extends RuntimeException {

    public PersonaException() {
    }

    public PersonaException(String message) {
        super(message);
    }

    public PersonaException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}
