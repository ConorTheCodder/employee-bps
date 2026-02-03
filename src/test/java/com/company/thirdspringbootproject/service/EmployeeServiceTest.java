package com.company.thirdspringbootproject.service;

import com.company.thirdspringbootproject.dto.EmployeeReqDTO;
import com.company.thirdspringbootproject.dto.EmployeeResDTO;
import com.company.thirdspringbootproject.model.Employee;
import com.company.thirdspringbootproject.repository.EmployeeRepository;
import org.glassfish.jaxb.core.v2.model.core.ID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;


    @Test
    void getByIdTest() {
        Integer one = 1;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName(".");
        employee.setPosition(".");
        employee.setAccountCreationDate(LocalDateTime.now());

        when(employeeRepository.findById(one)).thenReturn(Optional.of(employee));
        EmployeeReqDTO result = employeeService.getById(1);

        assertNotNull(result);
        assertEquals(employee.getName(), result.getName());
        assertEquals(employee.getPosition(), result.getPosition());
    }

    @Test
    void createAnEmployeeTest() {
        Employee employee = new Employee();
        employee.setName(".");
        employee.setPosition(".");

        EmployeeReqDTO employeeReqDTO = new EmployeeReqDTO();
        employeeReqDTO.setName(".");
        employeeReqDTO.setPosition(".");

        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        employeeService.createAnEmployee(employeeReqDTO);

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void getAllTest() {

        Employee employee = new Employee();
        employee.setName(".");
        employee.setPosition(".");

        EmployeeResDTO employeeResDTO = new EmployeeResDTO();
        employee.setName(".");
        employee.setPosition(".");
        employeeResDTO.setEmploymentDate(LocalDateTime.now());

        List<Employee> employeeList = Arrays.asList(employee);
        List<EmployeeResDTO> employeeResDTOList = Arrays.asList(employeeResDTO);

        when(employeeRepository.findAll()).thenReturn(employeeList);
        Collection<EmployeeResDTO> result = employeeService.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(employee.getName(), String.valueOf(result.stream().map(EmployeeResDTO::getName).findFirst()));
        assertEquals(employee.getPosition(), String.valueOf(result.stream().map(EmployeeResDTO::getPosition).findFirst()));
    }

    @Test
    void updateTest(){

        Employee returnEmployee = new Employee();
        returnEmployee.setName(".");
        returnEmployee.setPosition(".");

        EmployeeReqDTO employeeReqDTO = new EmployeeReqDTO();

        when(employeeRepository.findById(1)).thenReturn(Optional.of(returnEmployee));

        when(employeeRepository.save(any(Employee.class))).thenReturn(returnEmployee);

        employeeService.update(employeeReqDTO, 1);

        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void deleteTest(){
        doNothing().when(employeeRepository).deleteById(100);
        employeeService.delete(1);
        verify(employeeRepository, times(1)).deleteById(1);
    }
}