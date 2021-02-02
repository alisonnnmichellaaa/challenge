package challenge.Services.Exceptions;

public class OnlyOneFilter extends RuntimeException {
    public OnlyOneFilter(String errorMessage) {
        super(errorMessage);
    }

}
