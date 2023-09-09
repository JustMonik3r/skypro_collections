package pro.sky.skypro_collections.exceptions;

public class EmployeeAlreadyExist extends RuntimeException {
    public EmployeeAlreadyExist (String message) {
        super (message);
    }
}
