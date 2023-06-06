package wevioz.social_network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TextLimitException extends RuntimeException {
    public TextLimitException(String field, int limit) {
        super("The field: " + field + " must not be longer than " + limit + " symbols");
    }
}