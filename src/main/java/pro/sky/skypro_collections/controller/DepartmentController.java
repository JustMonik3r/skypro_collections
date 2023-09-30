package pro.sky.skypro_collections.controller;

import ch.qos.logback.core.model.conditional.ElseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skypro_collections.model.Employee;
import pro.sky.skypro_collections.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
class DepartmentController {
    private final DepartmentService departmentService;

    DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam Integer departmentId){
        return departmentService.getEmployeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam Integer departmentId){
        return departmentService.getEmployeeWithMinSalary(departmentId);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> getEmployeesFromDepartment(@RequestParam Integer departmentId){
        return departmentService.getEmployee(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesFromDepartment(){
        return departmentService.getEmployee();
    }
}
