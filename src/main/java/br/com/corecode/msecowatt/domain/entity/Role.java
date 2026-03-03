package br.com.corecode.msecowatt.domain.entity;

import br.com.corecode.msecowatt.domain.exceptions.InvalidDomainValueException;

import java.time.LocalDateTime;

public class Role {
    private final String id;
    private final String name;
    private LocalDateTime createdAt;

    public Role(String id, String name) {
        if(name == null || name.isBlank()){
            throw new InvalidDomainValueException("Role name cannot be null or blank");
        }

        this.id = id;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public static Role restore(String id, String name){
        Role role = new Role(id, name);
        role.createdAt = LocalDateTime.now();
        return role;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
