package wevioz.social_network.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String field) {
        super("The value in the field: " + field + " not found");
    }
}