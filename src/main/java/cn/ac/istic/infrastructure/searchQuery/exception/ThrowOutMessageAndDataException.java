package cn.ac.istic.infrastructure.searchQuery.exception;

public class ThrowOutMessageAndDataException extends ThrowOutMessageException {
    private final Object data;

    public ThrowOutMessageAndDataException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
