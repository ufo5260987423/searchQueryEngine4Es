package cn.ac.istic.infrastructure.searchQuery.exception;

public class ThrowOutMessageException extends Throwable {
    private final String message;

    public ThrowOutMessageException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
