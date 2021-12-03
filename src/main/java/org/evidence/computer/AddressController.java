package org.evidence.computer;

import java.net.URI;
import java.util.List;

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
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
class AddressController {

    private final AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        var addresses = addressRepository.findAll();
        return (addresses.isEmpty()) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<Object> createAddress(@RequestBody Address address) {
        addressRepository.save(address);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(address.getId())
                        .toUri();
        return ResponseEntity.created(location).body(address);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        var address = addressRepository.findById(id);
        return (address.isEmpty()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(address.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address, @PathVariable Long id) {
        return addressRepository.findById(id)
        .map(storedAddress -> {
            storedAddress.setStreet(address.getStreet());
            storedAddress.setCity(address.getCity());
            storedAddress.setZip(address.getZip());
            addressRepository.save(storedAddress);
            return ResponseEntity.ok(storedAddress);
        }).orElseGet(() -> {
            throw new AddressNotFoundException(id);
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deleteAddress(@PathVariable Long id) {
        return addressRepository.findById(id).map(address -> {
            addressRepository.delete(address);
            return ResponseEntity.ok(address);
        }).orElseGet(() -> {
            throw new AddressNotFoundException(id);
        });
    }
}
