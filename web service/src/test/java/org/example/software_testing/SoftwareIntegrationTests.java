package org.example.software_testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class) // Enable Spring support for JUnit 5
@SpringBootTest // Load the Spring application context
@AutoConfigureMockMvc // Autoconfigure the MockMvc instance
public class SoftwareIntegrationTests {
    @Autowired
    private MockMvc mockMvc;// Autowire MockMvc for performing HTTP requests

    // Test case to verify all admissions for a specific patient
    @Test
    public void testAllAdmissionsForSpecificPatient() throws Exception {
        // Expected JSON response for the test case
        String expectedJson = "[{\"id\":2,\"admissionDate\":\"2020-12-07T22:14:00.000+00:00\",\"dischargeDate\":\"0001-01-01T00:00:00.000+00:00\",\"patientID\":1}]";
        // Perform GET request and compare actual JSON response with the expected JSON
        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/all_admissions_for_a_specific_patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())// Verify HTTP status code is OK
                .andReturn().getResponse().getContentAsString();// Extract response content as String
        assertEquals(expectedJson, actualJson);// Assert expected and actual JSON responses are equal
    }
    // Test case to verify the staff member with the most admissions
    @Test
    public void testStaffHasMostAdmissions() throws Exception {
        // Expected JSON response for the test case
        String expectedJson = "{\"id\":4,\"surname\":\"Jones\",\"forename\":\"Sarah\"}";
        // Perform GET request and compare actual JSON response with the expected JSON
        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/staff_has_the_most_admissions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())// Verify HTTP status code is OK
                .andReturn().getResponse().getContentAsString();// Extract response content as String
        assertEquals(expectedJson, actualJson);// Assert expected and actual JSON responses are equal
    }
    // Test case to verify all staff who have no admissions
    @Test
    public void testAllStaffWhoHaveNoAdmissions() throws Exception {
        // Expected JSON response for the test case
        String expectedJson = "[{\"id\":1,\"surname\":\"Finley\",\"forename\":\"Sarah\"}," +
                "{\"id\":2,\"surname\":\"Jackson\",\"forename\":\"Robert\"},{\"id\":5,\"surname\":\"Wicks\",\"forename\":\"Patrick\"}]";
        // Perform GET request and compare actual JSON response with the expected JSON
        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/all_staff_who_have_no_admissions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())// Verify HTTP status code is OK
                .andReturn().getResponse().getContentAsString();// Extract response content as String
        assertEquals(expectedJson, actualJson);// Assert expected and actual JSON responses are equal
    }
    // Test case to verify all admissions that have not been discharged yet
    @Test
    public void testAllAdmissionsNotBeenDischargedYet() throws Exception {
        // Expected JSON response for the test case
        String expectedJson = "[{\"id\":2,\"admissionDate\":\"2020-12-07T22:14:00.000+00:00\",\"dischargeDate\":\"0001-01-01T00:00:00.000+00:00\",\"patientID\":1}," +
                "{\"id\":6,\"admissionDate\":\"2024-04-19T21:50:00.000+00:00\",\"dischargeDate\":\"0001-01-01T00:00:00.000+00:00\",\"patientID\":5}]";
        // Perform GET request and compare actual JSON response with the expected JSON
        String actualJson = mockMvc.perform(MockMvcRequestBuilders.get("/api/All_admissions_not_been_discharged_yet")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())// Verify HTTP status code is OK
                .andReturn().getResponse().getContentAsString();// Extract response content as String
        assertEquals(expectedJson, actualJson);// Assert expected and actual JSON responses are equal
    }
}
