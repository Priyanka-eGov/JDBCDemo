package com.example.demo.controller;

//import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repo.EmployeeRepo;
import com.example.demo.model.Employee;

import java.util.*;

//mapping request data to the defined request handler method - @Controller and @ResponseBody
//converts the response to JSON or XML.
//maps HTTP requests to handler methods 
//maps a specific request path or pattern onto a controller
@RestController
@RequestMapping("/api/vers")
public class EmployeeController {

	//for database operations
	@Autowired
	private EmployeeRepo empRepo;
	
	//get all employee
	//mapping HTTP GET requests onto this methods
	@GetMapping("/employees")
	public List<Employee> getAllEmp()
	{
		//retrieves all employees
        return empRepo.findAll();
	}
	
	
	//get employee by id
	//binding uri parameter{id} to the method arg by using @pathVariable
	//ResponseEntity-HTTP request or response , headers | body | Http status code.
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long employeeId)
	{
	      Employee employee = empRepo.findById(employeeId);
	      return ResponseEntity.ok().body(employee);
	}
	
	//save employee
	// used to map to POST requests 
	@PostMapping("/employees")
    public Employee createEmployee( @RequestBody Employee employee) {
        return empRepo.save(employee);
    }

	//@RequestBody - internally converts json -> java
    //update emp
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @RequestBody Employee employeeDetails)  {
//        Employee employee = empRepo.findById(employeeId);
//        employee.setEmail(employeeDetails.getEmail());
//        employee.setLastname(employeeDetails.getLastname());
//        employee.setFirstname(employeeDetails.getFirstname());
        empRepo.update(employeeDetails,employeeId);
//        final Employee updatedEmployee = empRepo.save(employee);
        return ResponseEntity.ok(employeeDetails);
    }

	//delete emp
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
         {
        Employee employee = empRepo.findById(employeeId);

        empRepo.delete(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	
}
