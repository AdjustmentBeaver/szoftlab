/**
 * Null attribute exception.
 * This exception should occur when an XML attribute is not specified, or it has an empty value
 */
public class NullAttributeException extends RuntimeException {
    public NullAttributeException() {
    }

    public NullAttributeException(String s) {
        super(s);
    }

    public NullAttributeException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NullAttributeException(Throwable throwable) {
        super(throwable);
    }

    public NullAttributeException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
