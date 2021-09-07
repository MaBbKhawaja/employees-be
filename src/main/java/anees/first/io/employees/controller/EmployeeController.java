package anees.first.io.employees.controller;

import anees.first.io.employees.entity.Employee;
import anees.first.io.employees.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
     private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    Employee postEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    Employee updatemployee(@RequestBody Employee employee, @PathVariable Long id){
        return employeeRepository.findById(id).map(updateEmployee -> {
            updateEmployee.setFirstName(employee.getFirstName());
            updateEmployee.setLastName(employee.getLastName());
            updateEmployee.setEmail(employee.getEmail());
            return employeeRepository.save(updateEmployee);
        }).orElseGet(() ->{
            employee.setId(id);
            return employeeRepository.save(employee);
        });
    }
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
    }


}
