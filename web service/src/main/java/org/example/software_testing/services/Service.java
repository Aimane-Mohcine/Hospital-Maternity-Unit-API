package org.example.software_testing.services;

import org.example.software_testing.entites.Admission;
import org.example.software_testing.entites.Allocation;
import org.example.software_testing.entites.Employee;
import org.example.software_testing.entites.Patient;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    String uri = "https://web.socem.plymouth.ac.uk/COMP2005/api/Admissions";
    String uri1 = "https://web.socem.plymouth.ac.uk/COMP2005/api/Allocations";
    String uri2 = "https://web.socem.plymouth.ac.uk/COMP2005/api/Employees";
    String uri3 = "https://web.socem.plymouth.ac.uk/COMP2005/api/Patients";

    public List<Admission> getAdmissions() {
        RestTemplate restTemplate = new RestTemplate();
        Admission[] admissions = restTemplate.getForObject(uri, Admission[].class);
        return Arrays.asList(admissions);
    }
    public Admission getoneAdmission(Integer id) {
        String uro = uri + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Admission admission = restTemplate.getForObject(uro, Admission.class);
        return admission;
    }
    public List<Allocation> getAllocations() {
        RestTemplate restTemplate = new RestTemplate();
        Allocation[] allocations = restTemplate.getForObject(uri1, Allocation[].class);
        return Arrays.asList(allocations);
    }
    public Allocation getoneAllocation(Integer id) {
        String uro = uri1 + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Allocation allocation = restTemplate.getForObject(uro, Allocation.class);
        return allocation;
    }
    public List<Employee> getEmployees() {
        RestTemplate restTemplate = new RestTemplate();
        Employee[] employees = restTemplate.getForObject(uri2, Employee[].class);
        return Arrays.asList(employees);
    }
    public Employee getoneEmployee(Integer id) {
        String uro = uri2 + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Employee employee = restTemplate.getForObject(uro, Employee.class);
        return employee;
    }
    public List<Patient> getPatient() {
        RestTemplate restTemplate = new RestTemplate();
        Patient[] patients = restTemplate.getForObject(uri3, Patient[].class);
        return Arrays.asList(patients);
    }
    public Patient getonePatient(Integer id) {
        String uro = uri3 + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Patient patient = restTemplate.getForObject(uro, Patient.class);
        return patient;
    }
}
