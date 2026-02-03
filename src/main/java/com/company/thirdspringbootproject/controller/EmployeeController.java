package com.company.thirdspringbootproject.controller;

import com.company.thirdspringbootproject.dto.EmployeeReqDTO;
import com.company.thirdspringbootproject.dto.EmployeeResDTO;
import com.company.thirdspringbootproject.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public void createAnEmployee(@RequestBody EmployeeReqDTO employeeReqDTO) {
        employeeService.createAnEmployee(employeeReqDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeReqDTO> getEmployeeById(@PathVariable Integer id) {

        try {
            EmployeeReqDTO employeeById = employeeService.getById(id);
            return ResponseEntity.ok(employeeById);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Collection<EmployeeResDTO> getAllEmployees(){
        return employeeService.getAll();
    }

    @PutMapping("/{id}")
    public void updateInfo(@RequestBody EmployeeReqDTO employeeReqDTO, @PathVariable Integer id){
        employeeService.update(employeeReqDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        employeeService.delete(id);
    }
}