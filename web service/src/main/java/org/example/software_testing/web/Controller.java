package org.example.software_testing.web;

import org.example.software_testing.entites.Admission;
import org.example.software_testing.entites.Allocation;
import org.example.software_testing.entites.Employee;
import org.example.software_testing.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/all_admissions_for_a_specific_patient/{ID}")
    public List<Admission> all_admissions_for_a_specific_patient(@PathVariable("ID") Integer patient_id) {
        List<Admission> all_admissions = service.getAdmissions();
        List<Admission> admissions = new ArrayList<Admission>();
        for (Admission admission : all_admissions) {
            if (admission.getPatientID() == patient_id) {
                admissions.add(admission);
            }
        }
        return admissions;
    }
    @GetMapping("/staff_has_the_most_admissions")
    public Employee staff_has_the_most_admissions() {
        List<Employee> all_employees = service.getEmployees();
        List<Allocation> all_allocations = service.getAllocations();
        Employee staff = all_employees.get(0);
        int count = 0;
        for (Employee employee : all_employees) {
            int n = 0;
            for (Allocation allocation : all_allocations) {
                if(allocation.getEmployeeID() == employee.getId()){
                    n++;
                }
            }
            if(count < n){
                staff = employee;
                count = n;
            }
        }
        return staff;
    }
    @GetMapping("/all_staff_who_have_no_admissions")
    public List<Employee> all_staff_who_have_no_admissions() {
        List<Employee> all_employees = service.getEmployees();
        List<Allocation> all_allocations = service.getAllocations();
        List<Employee> all_staff = new ArrayList<Employee>();
        for (Employee employee : all_employees) {
            int n = 0;
            for (Allocation allocation : all_allocations) {
                if(allocation.getEmployeeID() == employee.getId()){
                    n++;
                }
            }
            if(n == 0){
                all_staff.add(employee);
            }
        }
        return all_staff;
    }
    @GetMapping("/All_admissions_not_been_discharged_yet")
    public List<Admission> All_admissions_not_been_discharged_yet() {
        List<Admission> all_admissions = service.getAdmissions();
        List<Admission> admissions = new ArrayList<Admission>();
        for (Admission admission : all_admissions) {
            if (admission.getDischargeDate() == null || admission.getDischargeDate().before(admission.getAdmissionDate())){
                admissions.add(admission);
            }
        }
        return admissions;
    }
}
