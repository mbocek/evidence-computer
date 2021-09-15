package org.evidence.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.evidence.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

class CustomRestExceptionHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenNoHandlerForHttpRequest_thenNotFound() {
        ApiError response = restTemplate.getForObject("/api/xx", ApiError.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
        assertEquals(1, response.getErrors().size());
        assertTrue(response.getErrors().get(0).contains("No handler found"));
    }
}
