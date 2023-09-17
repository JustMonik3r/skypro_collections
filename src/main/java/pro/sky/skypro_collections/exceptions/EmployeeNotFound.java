package pro.sky.skypro_collections.exceptions;

public class EmployeeNotFound extends RuntimeException {
    public EmployeeNotFound (String message) {
        super (message);
    }
}
