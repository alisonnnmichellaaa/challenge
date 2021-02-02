package challenge.Services.Exceptions;

public class UserPermissionValidationException extends RuntimeException {
    public UserPermissionValidationException(String errorMessage) {
        super(errorMessage);
    }
}
