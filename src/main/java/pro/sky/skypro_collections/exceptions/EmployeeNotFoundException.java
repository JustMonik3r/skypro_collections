package pro.sky.skypro_collections.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super (message);
    }
}
