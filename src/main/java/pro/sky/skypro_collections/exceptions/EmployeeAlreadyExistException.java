package pro.sky.skypro_collections.exceptions;

public class EmployeeAlreadyExistException extends RuntimeException {
    public EmployeeAlreadyExistException(String message) {
        super (message);
    }
}
