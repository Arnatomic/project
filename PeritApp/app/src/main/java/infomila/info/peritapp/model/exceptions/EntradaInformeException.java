package infomila.info.peritapp.model.exceptions;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class EntradaInformeException extends RuntimeException {

    public EntradaInformeException() {
    }

    public EntradaInformeException(String message) {
        super(message);
    }

    public EntradaInformeException(String message, Throwable cause) {
        super(message, cause);
    }

}