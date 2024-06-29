package com.example.employee.demo.REST;

import com.example.employee.demo.DAO.EmployeeService;
import com.example.employee.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // prefix -> api
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee employee = employeeService.getEmployee(employeeId);
        if(employee == null){
            throw new EmployeeNotFoundException("Employee not found with id : " + employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee){
        Employee existedEmployee = employeeService.getEmployee(employeeId);
        if(existedEmployee == null){
            throw new EmployeeNotFoundException("Employee not found with id : " + employeeId);
        }
        existedEmployee.setFirstName(employee.getFirstName());
        existedEmployee.setLastName(employee.getLastName());
        existedEmployee.setEmail(employee.getEmail());

        return employeeService.saveEmployee(existedEmployee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
