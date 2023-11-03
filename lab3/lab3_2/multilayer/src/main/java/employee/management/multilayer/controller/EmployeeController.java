package employee.management.multilayer.controller;

import lombok.AllArgsConstructor;
import employee.management.multilayer.entity.Employee;
import employee.management.multilayer.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    // build create User REST API
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(name = "email", required = false) String email) {
      List<Employee> employees;
      if (email != null) {
        employees = employeeService.getEmployeeByEmail(email);
      } else {
        employees = employeeService.getAllEmployees();
      }
      return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // build get user by id REST API
    // http://localhost:8080/api/v1/employees/1
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("/employees/{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long employeeId,
                                           @RequestBody Employee employee){
        employee.setId(employeeId);
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }
}