package pro.sky.skypro_collections.exceptions;

public class EmployeesStorageIsFull extends RuntimeException {
    public EmployeesStorageIsFull (String message) {
        super (message);
    }
}
