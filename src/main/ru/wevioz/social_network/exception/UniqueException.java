package wevioz.social_network.exception;

import lombok.Getter;

@Getter
public class UniqueException extends Exception{
    public UniqueException(String field) {
        super("The field: " + field + " must be unique");
    }
}