package br.com.corecode.msecowatt.infrastructure.persistence.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "roles")
public class RoleDocument {

    @Id
    private String id;

    private String name;

    private LocalDateTime createdAt;

    public RoleDocument() {}

    public RoleDocument(String id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
