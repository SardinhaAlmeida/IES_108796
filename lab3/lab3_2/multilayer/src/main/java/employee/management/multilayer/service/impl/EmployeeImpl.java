package employee.management.multilayer.service.impl;

import lombok.AllArgsConstructor;
import employee.management.multilayer.entity.Employee;
import employee.management.multilayer.repository.EmployeeRepository;
import employee.management.multilayer.service.EmployeeService;
//import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import java.util.List;
//import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long userId) {
        Optional<Employee> optionalUser = employeeRepository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<Employee> getEmployeeByEmail(String email) {
      return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = employeeRepository.findById(employee.getId()).get();
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return updatedEmployee;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
