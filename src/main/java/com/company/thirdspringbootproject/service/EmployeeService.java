package com.company.thirdspringbootproject.service;

import com.company.thirdspringbootproject.dto.EmployeeReqDTO;
import com.company.thirdspringbootproject.dto.EmployeeResDTO;
import com.company.thirdspringbootproject.model.Employee;
import com.company.thirdspringbootproject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeReqDTO getById(Integer id){
        Employee employeeById = employeeRepository.findById(id).orElseThrow();
        return mapToReqDTO(employeeById);
    }

    public void createAnEmployee(EmployeeReqDTO employeeDTO){
        Employee employee = mapToEntity(employeeDTO);
        employeeRepository.save(employee);
    }

    public Collection<EmployeeResDTO> getAll(){
        List<Employee> allEmployees = employeeRepository.findAll();
        List<EmployeeResDTO> allEmployeesCorrectList = new ArrayList<>();

        for (Employee employee : allEmployees) {
            EmployeeResDTO employeeResDTO = mapToResDTO(employee);
            allEmployeesCorrectList.add(employeeResDTO);
        }
        return allEmployeesCorrectList;
    }

    public void update(EmployeeReqDTO employeeReqDTO, Integer id){

        Employee byId = employeeRepository.findById(id).orElseThrow();
        byId.setName(employeeReqDTO.getName());
        byId.setPosition(employeeReqDTO.getPosition());
        employeeRepository.save(byId);
    }

    public void delete(Integer id){
        employeeRepository.deleteById(id);
    }

    private Employee mapToEntity(EmployeeReqDTO employeeDTO){
        Employee employeeNew = new Employee();
        employeeNew.setName(employeeDTO.getName());
        employeeNew.setPosition(employeeDTO.getPosition());
        return employeeNew;
    }

    private EmployeeReqDTO mapToReqDTO(Employee employee){
        EmployeeReqDTO employeeDTO = new EmployeeReqDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setPosition(employee.getPosition());
        return employeeDTO;
    }

    private EmployeeResDTO mapToResDTO(Employee employee){
        EmployeeResDTO employeeResDTO = new EmployeeResDTO();
        employeeResDTO.setEmploymentDate((employee.getAccountCreationDate()));
        employeeResDTO.setName(employee.getName());
        employeeResDTO.setPosition(employee.getPosition());
        return employeeResDTO;
    }
}