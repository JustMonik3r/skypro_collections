package pro.sky.skypro_collections.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skypro_collections.model.Employee;
import pro.sky.skypro_collections.service.*;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.get(firstName, lastName);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
}
