package wevioz.social_network.exception;

public class TextLimitException extends Exception {
    public TextLimitException(String field, int limit) {
        super("The field: " + field + " must not be longer than " + limit + " symbols");
    }
}