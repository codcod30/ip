package oguricap.exception;

/**
 * Custom exception class for handling Duke-related errors.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public DukeException(String message) {
        super(message);
    }
}
