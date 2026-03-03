package br.com.corecode.msecowatt.domain.entity;

import java.time.LocalDateTime;
import java.util.Set;

public class User {

    private String id;
    private final String username;
    private final String password;
    private final Set<Role> roles;
    private LocalDateTime createdAt;

    public User(String id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.createdAt = LocalDateTime.now();
    }

    public static User restore(
            String id, String username, String password, Set<Role> roles
    ){
        User user = new User(id, username, password, roles);
        user.createdAt = LocalDateTime.now();
        return user;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    public boolean hasRole(String roleName) {
        return roles.stream()
                .anyMatch(role -> role.getName().equals(roleName));
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
