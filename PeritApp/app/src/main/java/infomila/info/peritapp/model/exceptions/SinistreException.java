package infomila.info.peritapp.model.exceptions;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class SinistreException extends RuntimeException {

    public SinistreException() {
    }

    public SinistreException(String message) {
        super(message);
    }

    public SinistreException(String message, Throwable cause) {
        super(message, cause);
    }

}