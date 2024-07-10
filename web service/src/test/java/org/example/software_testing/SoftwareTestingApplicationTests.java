package org.example.software_testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.software_testing.entites.Admission;
import org.example.software_testing.entites.Allocation;
import org.example.software_testing.entites.Employee;
import org.example.software_testing.services.Service;
import org.example.software_testing.web.Controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)// Use MockitoJUnitRunner for running tests with Mockito
public class SoftwareTestingApplicationTests {

    @Mock
    private Service service;// Mock the Service dependency

    @InjectMocks
    private Controller controller; // Inject the mocks into Controller

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);// Initialize the mocks
    }

    // Test for All Admissions ForSpecific Patient method
    @Test
    public void test_All_Admissions_ForSpecific_Patient() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        int patientId = 1;
        // Create a sample list of admissions
        List<Admission> admissions = Arrays.asList(new Admission(2,format.parse("Mon Dec 07 23:14:00 WEST 2020"),
                format.parse("Sat Jan 01 00:00:00 WET 1"),1));
        // Read admissions from a JSON file (mocking data)
        List<Admission> admissions1 = readAdmissionsFromFile("src/test/java/org/example/software_testing/data/admissions.json");
        // Mock the service method to return the admissions data
        when(service.getAdmissions()).thenReturn(admissions1);
        // Call the controller method and compare the result with expected admissions
        List<Admission> result = controller.all_admissions_for_a_specific_patient(patientId);
        assertEquals(admissions, result);
    }

    // Test for staff has the most admissions method
    @Test
    public void testStaffHasTheMostAdmissions() {
        // Test for staff has the most admissions method
        Employee employee = new Employee(4,"Jones","Sarah");
        // Read employee data from a JSON file (mocking data)
        List<Employee> employees = readEmployeeFromFile("src/test/java/org/example/software_testing/data/employee.json") ;
        // Read allocation data from a JSON file (mocking data)
        List<Allocation> allocations = readAllocationsFromFile("src/test/java/org/example/software_testing/data/allocation.json");
        // Mock the service methods to return the employee and allocation data
        when(service.getEmployees()).thenReturn(employees);
        when(service.getAllocations()).thenReturn(allocations);
        // Call the controller method and compare the result with expected employee
        Employee staffWithMostAdmissions = controller.staff_has_the_most_admissions();
       assertEquals(employee, staffWithMostAdmissions);
    }

    // Test for all staff who have no admissions method
    @Test
    public void testAllStaffWhoHaveNoAdmissions() {
        List<Employee> staffs = Arrays.asList(new Employee(1,"Finley","Sarah"),
                new Employee(2,"Jackson","Robert"), new Employee(5,"Wicks","Patrick"));
        // Read employee data from a JSON file (mocking data)
        List<Employee> employees = readEmployeeFromFile("src/test/java/org/example/software_testing/data/employee.json") ;
        // Read allocation data from a JSON file (mocking data)
        List<Allocation> allocations = readAllocationsFromFile("src/test/java/org/example/software_testing/data/allocation.json");
        // Mock the service methods to return the employee and allocation data
        when(service.getEmployees()).thenReturn(employees);
        when(service.getAllocations()).thenReturn(allocations);
        // Call the controller method and compare the result with expected staff list
        List<Employee> staffWithNoAdmissions = controller.all_staff_who_have_no_admissions();
        assertEquals(staffs, staffWithNoAdmissions);
    }

    // Test for All admissions not been discharged yet method
    @Test
    public void test_All_Admissions_Not_Been_Discharged_Yet() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        List<Admission> admissionslist = Arrays.asList(new Admission(2,format.parse("Mon Dec 07 23:14:00 WEST 2020"),
                format.parse("Sat Jan 01 00:00:00 WET 1"),1));
        // Read admissions data from a JSON file (mocking data)
        List<Admission> admissions = readAdmissionsFromFile("src/test/java/org/example/software_testing/data/admissions.json");
        // Mock the service method to return the admissions data
        when(service.getAdmissions()).thenReturn(admissions);
        // Call the controller method and compare the result with expected admissions list
        List<Admission> result = controller.All_admissions_not_been_discharged_yet();
        assertEquals(admissionslist, result);
    }


    // Method to read admissions from a JSON file
    private List<Admission> readAdmissionsFromFile(String filename) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();
            // Read JSON file and parse it into a list of Admission objects
            File file = new File(filename);
            Admission[] admissionsArray = objectMapper.readValue(file, Admission[].class); // Parse JSON into Admission array
            return Arrays.asList(admissionsArray); // Return list of admissions
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return empty list if an error occurs
        }
    }
    private List<Allocation> readAllocationsFromFile(String filename) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and parse it into a list of Allocation objects
            File file = new File(filename);
            Allocation[] allocationsArray = objectMapper.readValue(file, Allocation[].class);// Parse JSON into Allocation array
            return Arrays.asList(allocationsArray); // Return list of Allocation
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return empty list if an error occurs
        }
    }
    private List<Employee> readEmployeeFromFile(String filename) {
        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file and parse it into a list of Employee objects
            File file = new File(filename);
            Employee[] employeesArray = objectMapper.readValue(file, Employee[].class);// Parse JSON into Employee array
            return Arrays.asList(employeesArray); // Return list of Employee
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return empty list if an error occurs
        }
    }
}
