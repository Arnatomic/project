package infomila.info.peritapp.model.exceptions;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class TrucadaException extends RuntimeException {

    public TrucadaException() {
    }

    public TrucadaException(String message) {
        super(message);
    }

    public TrucadaException(String message, Throwable cause) {
        super(message, cause);
    }

}