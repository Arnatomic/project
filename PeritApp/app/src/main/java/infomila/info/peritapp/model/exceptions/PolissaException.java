package infomila.info.peritapp.model.exceptions;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class PolissaException extends RuntimeException {

    public PolissaException() {
    }

    public PolissaException(String message) {
        super(message);
    }

    public PolissaException(String message, Throwable cause) {
        super(message, cause);
    }

}