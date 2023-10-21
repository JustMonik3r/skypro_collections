package pro.sky.skypro_collections.service;

import pro.sky.skypro_collections.model.Employee;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {
    public static final String FIRST_NAME = "Ivan";
    public static final String LAST_NAME = "Ivanov";
    public static final double SALARY = 30000;

    public static final String FIRST_NAME_2 = "Sergey";
    public static final String LAST_NAME_2 = "Sergeev";
    public static final double SALARY_2 = 20000;

    public static final String FIRST_NAME_3 = "Nikolai";
    public static final String LAST_NAME_3 = "Nikolaev";
    public static final double SALARY_3 = 10000;


    public static final int FIRST_DEPARTMENT_ID = 1;

    public static final int SECOND_DEPARTMENT_ID = 2;


    public static Employee getEmployee() {
        return new Employee(FIRST_NAME, LAST_NAME, FIRST_DEPARTMENT_ID, SALARY);
    }

    public static Employee getEmployee2() {
        return new Employee(FIRST_NAME_2, LAST_NAME_2, FIRST_DEPARTMENT_ID, SALARY_2);
    }

    public static Employee getEmployee3() {
        return new Employee(FIRST_NAME_3, LAST_NAME_3, SECOND_DEPARTMENT_ID, SALARY_3);
    }

    public static List<Employee> getAllEmployee() {
        return Arrays.asList(getEmployee(), getEmployee2(), getEmployee3());
    }
}
