package org.example.software_testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)// Enable Spring support for JUnit 5
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Load the Spring application context with a random port
public class SoftwareSystmeTest {


    @Autowired
    private TestRestTemplate restTemplate;// Autowire TestRestTemplate for making HTTP requests

    @Autowired
    private ObjectMapper objectMapper;// Autowire ObjectMapper for parsing JSON

    // Test case to verify all admissions for a specific patient
    @Test
    public void testAllAdmissionsForSpecificPatient() throws Exception {
        // Send GET request to the specified endpoint
        ResponseEntity<String> response = restTemplate.getForEntity("/api/all_admissions_for_a_specific_patient/1", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());// Verify that the HTTP status code is OK
        // Assert that the response body is not empty
        assertTrue(response.getBody() != null && !response.getBody().isEmpty());
        // Parse the response body as JSON
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        assertTrue(jsonNode.isArray()); // Assert that the response is a JSON array
        assertNotNull(jsonNode);// Assert that the parsed JSON is not null
    }

    // Test case to verify the staff member with the most admissions
    @Test
    public void testStaffHasMostAdmissions() throws Exception {
        // Send GET request to the specified endpoint
        ResponseEntity<String> response = restTemplate.getForEntity("/api/staff_has_the_most_admissions", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());// Verify that the HTTP status code is OK
        // Assert that the response body is not empty
        assertTrue(response.getBody() != null && !response.getBody().isEmpty());
        // Parse the response body as JSON
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        assertFalse(jsonNode.isArray());// Assert that the response is a JSON array
        assertNotNull(jsonNode);// Assert that the parsed JSON is not null
    }

    // Test case to verify all staff who have no admissions
    @Test
    public void testAllStaffWhoHaveNoAdmissions() throws Exception {
        // Send GET request to the specified endpoint
        ResponseEntity<String> response = restTemplate.getForEntity("/api/all_staff_who_have_no_admissions", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());// Verify that the HTTP status code is OK
        // Assert that the response body is not empty
        assertTrue(response.getBody() != null && !response.getBody().isEmpty());
        // Parse the response body as JSON
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        assertTrue(jsonNode.isArray());// Assert that the response is a JSON array
        assertNotNull(jsonNode);// Assert that the parsed JSON is not null
    }

    // Test case to verify all admissions that have not been discharged yet
    @Test
    public void testAllAdmissionsNotBeenDischargedYet() throws Exception {
        // Send GET request to the specified endpoint
        ResponseEntity<String> response = restTemplate.getForEntity("/api/All_admissions_not_been_discharged_yet", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());// Verify that the HTTP status code is OK
        // Assert that the response body is not empty
        assertTrue(response.getBody() != null && !response.getBody().isEmpty());
        // Parse the response body as JSON
        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        assertTrue(jsonNode.isArray());// Assert that the response is a JSON array
        assertNotNull(jsonNode);// Assert that the parsed JSON is not null
    }
}

