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

import java.time.LocalDate;
import java.util.List;

@SqlGroup({
    @Sql(scripts = {"/sql/address.sql", "/sql/computer.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
    @Sql(scripts = {"/sql/cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class ComputerControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void getExistingComputer() {
        var entity = restTemplate.getForEntity("/api/computers/1000000", Computer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getNonExistingComputer() {
        var entity = restTemplate.getForEntity("/api/computers/-1", Computer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getAllComputers() {
        var entity = restTemplate.exchange("/api/computers", HttpMethod.GET, null,
                                           new ParameterizedTypeReference<List<Computer>>() {});
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().size()).isPositive();
    }

    @Test
    void createComputer() {
        var computer = getComputer();
        var entity = restTemplate.postForEntity("/api/computers", computer, Computer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(entity.getHeaders().getLocation().getPath()).matches("^.+?\\d$");
    }

    @Test
    void deleteComputer() {
        var entity = restTemplate.exchange("/api/computers/1000000", HttpMethod.DELETE, null,
                                            new ParameterizedTypeReference<Computer>() {});
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody().getId()).isEqualTo(1000000);
    }

    private Object getComputer() {
        var computer = new Computer();
        computer.setName("computer-1");
        computer.setDomain("domain.lan");
        computer.setPurchaseDate(LocalDate.now());
        computer.setVendor("vendor-1");
        computer.setLocality(addressRepository.findById(1000000L).get());
        return computer;
    }

}
