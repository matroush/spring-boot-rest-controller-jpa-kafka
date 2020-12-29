package com.peopleflw.ems.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.peopleflw.ems.dao.EmployeeRepo;
import com.peopleflw.ems.enums.EmployeeState;
import com.peopleflw.ems.exceptions.InvalidEmployeeStateException;
import com.peopleflw.ems.kafka.SendMessageTask;
import com.peopleflw.ems.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    SendMessageTask kafkaMsgSenderTask;

    @Transactional
    public Employee save(Employee entity) {
        entity.setState(EmployeeState.ADDED);
        Employee createdEmployee = employeeRepo.save(entity);

        logger.info("an employee with id "+createdEmployee.getId()+" created successfuly");

        try {
            String serializedMessage = new ObjectMapper().writeValueAsString(createdEmployee);
            logger.info(serializedMessage);
            kafkaMsgSenderTask.send(serializedMessage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } 

        return createdEmployee;
    }

   
    @Transactional
    public Employee changEmployeeState(Integer id , EmployeeState state) throws Exception{

        Optional<Employee> entity =  employeeRepo.findById(id);

        if(entity.isPresent()){


            Employee e = entity.get();

            //Simple Business validation 

            if(EmployeeState.ADDED == state 
            || ( EmployeeState.In_CHECK == state && e.getState() !=EmployeeState.ADDED)
            || ( EmployeeState.APPROVED == state && e.getState() !=EmployeeState.In_CHECK)
            || ( EmployeeState.ACTIVE == state && e.getState() !=EmployeeState.APPROVED)
              ) {
                  throw new InvalidEmployeeStateException("Invalid state ");
              }



            e.setState(state);
            return employeeRepo.save(e);

        }

        throw new EntityNotFoundException("No Employee with id "+ id);



    }




}
