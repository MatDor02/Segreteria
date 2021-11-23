package myExceptions;

public class HourFormatException extends Exception {
    public HourFormatException() {
        super();
    }

    public HourFormatException(String message) {
        super(message);
    }
}
