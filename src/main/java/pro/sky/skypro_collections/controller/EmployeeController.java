package pro.sky.skypro_collections.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skypro_collections.model.Employee;
import pro.sky.skypro_collections.service.*;

import java.util.Collection;


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
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.add(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.remove(firstName, lastName, department, salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam double salary) {
        return employeeService.get(firstName, lastName, department, salary);
    }

    @GetMapping("/getall")
    public Collection<Employee> getAll() {
        return employeeService.getAll();
    }
}
