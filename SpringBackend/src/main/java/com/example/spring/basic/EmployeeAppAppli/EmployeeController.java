package com.example.spring.basic.EmployeeAppAppli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:3000/")
public class EmployeeController {
@Autowired
private EmployeeRepository repo;
@GetMapping("/employee")
public List<Employee>getAllEmployee(){
	return repo.findAll();
	}
@PostMapping("/employee") 
public Employee createEmployee(@RequestBody Employee employee){
	return repo.save(employee);
}
@GetMapping("/employee/{id}")
public ResponseEntity <Employee> getEmployeeById(@PathVariable int id)
{
	Employee employee=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee data doesnot Exists+id"));
			return ResponseEntity.ok(employee);
}

	@PutMapping("employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employeeDetails)
	{
	Employee employee=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee Not Exits "+id));
	employee.setFirstName(employeeDetails.getFirstName());
	employee.setLastName(employeeDetails.getLastName());
    employee.setEmailId(employeeDetails.getEmailId());
    Employee updateEmployee=repo.save(employee);
    return ResponseEntity.ok(updateEmployee);
}
@DeleteMapping("/employee/{id}")
public ResponseEntity<Map<String,Boolean>>deleteEmployee(@PathVariable int id)
{
	Employee employee=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee data not Exists"+id));
	repo.delete(employee);
	Map<String,Boolean>response=new HashMap();
	response.put("deleted",Boolean.TRUE);
	return ResponseEntity.ok(response);
}
}
