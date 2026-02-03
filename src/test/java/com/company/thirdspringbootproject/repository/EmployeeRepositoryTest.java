package com.company.thirdspringbootproject.repository;

import com.company.thirdspringbootproject.model.Employee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.support.TransactionTemplate;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@Transactional
class EmployeeRepositoryTest {

    private Employee employee;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("postgres_db_test")
            .withUsername("user_test")
            .withPassword("password_test");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }

    @Autowired
    private TransactionTemplate transactionTemplate;

    @BeforeAll
    public static void startContainers() {
        postgreSQLContainer.start();
    }

    @AfterAll
    public static void endContainers() {
        postgreSQLContainer.stop();
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setName(".");
        employee.setPosition(".");
    }

    @Test
    void saveTest() {

        Employee employeeSaved = employeeRepository.save(employee);
        List<Employee> allEmployees = employeeRepository.findAll();

        assertNotNull(allEmployees);
        Employee employee1 = allEmployees.get(0);
        assertEquals(employee.getName(), employee1.getName());
    }

    @Test
    void findAllTest() {

        employeeRepository.save(employee);
        List<Employee> all = employeeRepository.findAll();
        assertNotNull(all);
        assertEquals(1, all.size());
    }

    @Test
    void findById(){
        employeeRepository.save(employee);
        Employee byId = employeeRepository.findById(employee.getId()).orElseThrow();

        assertNotNull(byId);
        assertEquals(employee.getName(), byId.getName());
        assertEquals(employee.getPosition(), byId.getPosition());
    }

    @Test
    void deleteById(){
        employeeRepository.save(employee);
        employeeRepository.deleteById(employee.getId());
        boolean isPresent = employeeRepository.findById(employee.getId()).isPresent();
        assertFalse(isPresent);
    }
}