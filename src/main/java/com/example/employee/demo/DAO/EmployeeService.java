package com.example.employee.demo.DAO;

import com.example.employee.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAllEmployees (){
        return employeeRepo.findAll();
    }

    public Employee getEmployee(int id){
        return employeeRepo.findById(id).orElse(null);
    }

    //both create and update
    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(int id){
        employeeRepo.deleteById(id);
    }
}
