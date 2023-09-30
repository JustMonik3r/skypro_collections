package pro.sky.skypro_collections.service;

import org.springframework.stereotype.Service;
import pro.sky.skypro_collections.exceptions.EmployeeNotFound;
import pro.sky.skypro_collections.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFound("Employee not found."));
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFound("Employee not found."));
    }

    @Override
    public Collection<Employee> getEmployee(Integer departmentId) {
        return employeeService.getAll()
                .stream()
                .filter(e -> e.getDepartment() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployee() {
        return employeeService.getAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
