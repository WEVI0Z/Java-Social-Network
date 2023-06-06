package wevioz.social_network.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UniqueException extends RuntimeException {
    public UniqueException(String field) {
        super("The field: " + field + " must be unique");
    }
}