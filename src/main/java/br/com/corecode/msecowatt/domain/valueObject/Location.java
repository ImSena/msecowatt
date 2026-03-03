package br.com.corecode.msecowatt.domain.valueObject;

import br.com.corecode.msecowatt.domain.exceptions.InvalidDomainValueException;

public record Location(String street, String city, String state) {
    public Location {
        if (street == null || city == null || state == null) {
            throw new InvalidDomainValueException("Address details cannot be null");
        }

    }
}
