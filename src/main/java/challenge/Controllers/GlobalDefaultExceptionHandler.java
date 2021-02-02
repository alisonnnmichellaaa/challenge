package challenge.Controllers;

import challenge.Services.Exceptions.JsonPlaceHolderServiceException;
import challenge.Services.Exceptions.OnlyOneFilter;
import challenge.Services.Exceptions.UserPermissionValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = UserPermissionValidationException.class)
    public ResponseEntity<String> handlePermissionsInvalid(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleIntegrityContraint(Exception ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The user already has album permissions");
    }
    @ExceptionHandler(value = JsonPlaceHolderServiceException.class)
    public ResponseEntity<String> handleJsonPlaceHolderServiceException(Exception ex){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
    }
    @ExceptionHandler(value = OnlyOneFilter.class)
    public ResponseEntity<String> handleOnlyOneFilter(Exception ex){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
    }

}
