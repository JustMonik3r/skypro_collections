package pro.sky.skypro_collections.service;

import pro.sky.skypro_collections.model.Employee;

import java.util.Collection;
import java.util.Map;
import java.util.List;

public interface DepartmentService {
    public Employee getEmployeeWithMaxSalary(Integer departmentId);

    public Employee getEmployeeWithMinSalary (Integer departmentId);

    Collection<Employee> getEmployee (Integer departmentId);

    Map<Integer, List<Employee>> getEmployee();

}
