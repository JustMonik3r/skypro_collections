package pro.sky.skypro_collections.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skypro_collections.exceptions.EmployeeNotFoundException;
import pro.sky.skypro_collections.model.Employee;

import java.util.*;

import static pro.sky.skypro_collections.service.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void getEmployeeWithMaxSalary_shouldFindEmployeeWithMaxSalary() {
        //Входные данные
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMaxSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMaxSalary_shouldThrowEmployeeNotFoundException() {
        //Входные данные
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "Сотрудник не найден.";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeeWithMinSalary_shouldFindEmployeeWithMinSalary() {
        //Входные данные
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Employee expectedEmployee = getEmployee2();

        //Начало теста
        Employee actualEmployee = departmentService.getEmployeeWithMinSalary(departmentId);
        assertEquals(expectedEmployee, actualEmployee);
        assertTrue(getEmployee().getSalary() > getEmployee2().getSalary());
    }

    @Test
    void getEmployeeWithMinSalary_shouldThrowEmployeeNotFoundException() {
        //Входные данные
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        when(employeeService.getAll()).thenReturn(Collections.emptyList());

        String expectedMessage = "Сотрудник не найден.";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(departmentId)
        );
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getEmployeesByDepartment_shouldFindAllEmployeesFromASelectedDepartment() {
        //Входные данные
        Integer departmentId = FIRST_DEPARTMENT_ID;

        //Ожидаемый результат
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee(), getEmployee2()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartment(), getEmployee2().getDepartment());
        assertNotEquals(getEmployee().getDepartment(), getEmployee3().getDepartment());
    }

    @Test
    void getEmployeesByDepartment_shouldFindAllEmployeesSortedByDepartments() {
        //Входные данные
        Integer departmentId = null;

        //Ожидаемый результат
        when(employeeService.getAll()).thenReturn(getAllEmployee());

        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(FIRST_DEPARTMENT_ID, Arrays.asList(getEmployee(), getEmployee2()));
        expectedMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(getEmployee3()));

        //Начало теста
        Map<Integer, List<Employee>> actualMap = departmentService.getEmployeesByDepartment(departmentId);
        assertEquals(expectedMap, actualMap);
        assertEquals(getEmployee().getDepartment(), getEmployee2().getDepartment());
        assertNotEquals(getEmployee().getDepartment(), getEmployee3().getDepartment());
    }
}