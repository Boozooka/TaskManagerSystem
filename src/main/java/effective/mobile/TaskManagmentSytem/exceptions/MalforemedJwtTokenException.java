package effective.mobile.TaskManagmentSytem.exceptions;

public class MalforemedJwtTokenException extends RuntimeException {
    public MalforemedJwtTokenException(String message) {
        super(message);
    }
}
