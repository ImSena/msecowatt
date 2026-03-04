package br.com.corecode.msecowatt.domain.entity;

import java.time.LocalDateTime;

public class Alert {

    private String id;
    private String companyId;
    private String type;
    private String message;
    private boolean resolved;
    private LocalDateTime createdAt;

    public Alert(String id, String companyId, String type, String message, boolean resolved, LocalDateTime createdAt) {
        this.id = id;
        this.companyId = companyId;
        this.type = type;
        this.message = message;
        this.resolved = resolved;
        this.createdAt = createdAt;
    }

    public Alert(String companyId, String type, String message) {
        this(null, companyId, type, message, false, LocalDateTime.now());
    }

    public String getId() {
        return id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResolved() {
        return resolved;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void resolve() {
        this.resolved = true;
    }
}
