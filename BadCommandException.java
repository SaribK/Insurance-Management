//custom command for error
class BadCommandException extends RuntimeException {
    BadCommandException(String message) {
        super(message);
    }
}
