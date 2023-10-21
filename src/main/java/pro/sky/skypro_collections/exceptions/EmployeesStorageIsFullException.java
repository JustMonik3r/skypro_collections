package pro.sky.skypro_collections.exceptions;

public class EmployeesStorageIsFullException extends RuntimeException {
    public EmployeesStorageIsFullException(String message) {
        super (message);
    }
}
