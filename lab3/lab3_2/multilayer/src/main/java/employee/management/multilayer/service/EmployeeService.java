package employee.management.multilayer.service;

import employee.management.multilayer.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee user);

    Employee getEmployeeById(Long userId);

    List<Employee> getEmployeeByEmail(String userEmail);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee user);

    void deleteEmployee(Long userId);
}
