package wevioz.social_network.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String field) {
        super("The value in the field: " + field + " not found");
    }
}