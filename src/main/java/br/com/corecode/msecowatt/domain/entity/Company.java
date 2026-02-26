package br.com.corecode.msecowatt.domain.entity;

import br.com.corecode.msecowatt.domain.exceptions.InvalidDomainValueException;
import br.com.corecode.msecowatt.domain.valueObject.Cnpj;
import br.com.corecode.msecowatt.domain.valueObject.Location;

import java.time.LocalDateTime;

public class Company {
    private final Long id;
    private final String name;
    private final Cnpj cnpj;
    private final Location location;
    private final String phoneNumber;
    private final LocalDateTime createdAt;

    public Company(Long id, String name, Cnpj cnpj, Location location, String phoneNumber) {
        if(name == null || name.isEmpty()){
            throw new InvalidDomainValueException("Company name cannot be null or empty");
        }

        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Cnpj getCnpj() {
        return cnpj;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Location getLocation() {
        return location;
    }
}