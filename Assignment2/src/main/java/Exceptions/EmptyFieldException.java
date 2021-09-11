
package Exceptions;

/**
 *
 * @author jolta
 */
public class EmptyFieldException extends Exception {
    
     public EmptyFieldException(String message) {
        super(message);
    }

    public EmptyFieldException() {
    }

    @Override
    public String getMessage() {
        
        return "Field already empty";
    }
}
