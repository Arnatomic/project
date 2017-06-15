package infomila.info.peritapp.model.exceptions;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class InformePericialException extends RuntimeException {

    public InformePericialException() {
    }

    public InformePericialException(String message) {
        super(message);
    }

    public InformePericialException(String message, Throwable cause) {
        super(message, cause);
    }

}