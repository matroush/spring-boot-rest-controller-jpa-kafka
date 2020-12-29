package com.peopleflw.ems.rest;

import com.peopleflw.ems.enums.EmployeeState;
import com.peopleflw.ems.model.Employee;
import com.peopleflw.ems.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
 
    @Autowired
    private EmployeeService employeeService;


 @PostMapping("/employees")
public Employee createEmployee(@RequestBody Employee Employee) {
	Employee savedEmployee = employeeService.save(Employee);

	return savedEmployee;

}

@PostMapping("/employees/{id}/state/incheck")
public ResponseEntity<String> inCheckEmployee(@PathVariable("id") int id) {

   
    try {
        employeeService.changEmployeeState(id,EmployeeState.In_CHECK);
        return new ResponseEntity<>("State Updated Successfuly",HttpStatus.OK);
    } catch (Exception e) {
    
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

@PostMapping("/employees/{id}/state/approve")
public ResponseEntity<String> approveEmployee(@PathVariable("id") int id) {

   
    try {
        employeeService.changEmployeeState(id,EmployeeState.APPROVED);
        return new ResponseEntity<>("State Updated Successfuly" , HttpStatus.OK);
    } catch (Exception e) {
    
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
@PostMapping("/employees/{id}/state/active")
public ResponseEntity<String> activateEmployee(@PathVariable("id") int id) {

   
    try {
        employeeService.changEmployeeState(id,EmployeeState.ACTIVE);
        return new ResponseEntity<>("State Updated Successfuly", HttpStatus.OK);
    } catch (Exception e) {
    
        return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
	 
   
  
	

 
}
