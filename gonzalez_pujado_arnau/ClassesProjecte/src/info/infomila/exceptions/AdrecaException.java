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
public class AdrecaException extends RuntimeException {

    public AdrecaException() {
    }

    public AdrecaException(String message) {
        super(message);
    }

    public AdrecaException(String message, Throwable cause) {
        super(message, cause);
    }

    
    
}
