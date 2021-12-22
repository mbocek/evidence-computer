package org.evidence.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.evidence.AbstractIntegrationTest;
import org.evidence.common.ApiError;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class CustomRestExceptionHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenNoHandlerForHttpRequest_thenNotFound() {
        var response = restTemplate.getForObject("/api/xx", ApiError.class);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getMessage()).startsWith("No handler found");
        assertThat(response.getDetail()).isNotEmpty();
    }
}
