public class NegativeAreaException extends RuntimeException {
    public NegativeAreaException(String errorMessage) {
        super(errorMessage);
    }
    public NegativeAreaException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
    public NegativeAreaException(Throwable err) {
        super(err);
    }

}
