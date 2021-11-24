package org.evidence.computer;

public class AddressNotFoundException extends RuntimeException {
    AddressNotFoundException(Long id) {
        super("Could not find address " + id);
    }
}
