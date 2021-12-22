package org.evidence.computer;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/computers")
@RequiredArgsConstructor
class ComputerController {

    private final ComputerRepository computerRepository;

    private final AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity<List<Computer>> getAllComputers() {
        var computers = computerRepository.findAll();
        return (computers.isEmpty()) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(computers);
    }

    @PostMapping
    public ResponseEntity<Computer> createComputer(@Valid @RequestBody Computer computer) {
        computerRepository.save(computer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(computer.getId())
                        .toUri();
        return ResponseEntity.created(location).body(computer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable Long id) {
        var computer = computerRepository.findById(id);
        return (computer.isEmpty()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(computer.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Computer> updateComputer(@Valid @RequestBody Computer computer, @PathVariable Long id) {
        return computerRepository.findById(id)
        .map(storedComputer -> {
            storedComputer.setName(computer.getName());
            storedComputer.setDomain(computer.getDomain());
            storedComputer.setPurchaseDate(computer.getPurchaseDate());
            var address = addressRepository.findById(computer.getLocality().getId()).get();
            storedComputer.setLocality(address);
            computerRepository.save(storedComputer);
            return ResponseEntity.ok(storedComputer);
        }).orElseGet(() -> {
            throw new ComputerNotFoundException(id);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Computer> deleteComputer(@PathVariable Long id) {
        return computerRepository.findById(id).map(computer -> {
            computerRepository.delete(computer);
            return ResponseEntity.ok(computer);
        }).orElseGet(() -> {
            throw new ComputerNotFoundException(id);
        });
    }
}
