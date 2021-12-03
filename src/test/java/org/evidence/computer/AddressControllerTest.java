package org.evidence.computer;

import org.evidence.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SqlGroup({
    @Sql(scripts = {"/sql/address.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(scripts = {"/sql/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class AddressControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getExistingAddress() {
        var entity = restTemplate.getForEntity("/api/addresses/1000000", Address.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void getNonExistingAddress() {
        var entity = restTemplate.getForEntity("/api/addresses/-1", Computer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getAllAddresses() {
        var entity = restTemplate.exchange("/api/addresses", HttpMethod.GET, null,
                                           new ParameterizedTypeReference<List<Address>>() {});
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isGreaterThan(0);
    }

    @Test
    public void createAddress() {
        var address = new Address();
        address.setCity("Prague");
        address.setStreet("Nám. Republiky 1078/1");
        address.setZip("110 00");
        var entity = restTemplate.postForEntity("/api/addresses", address, Address.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation().getPath()).matches("^.+?\\d$");
    }

    @Test
    public void deleteAddress() {
        var entity = restTemplate.exchange("/api/addresses/1000000", HttpMethod.DELETE, null,
                                            new ParameterizedTypeReference<Address>() {});
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getId()).isEqualTo(1000000);
    }
}
