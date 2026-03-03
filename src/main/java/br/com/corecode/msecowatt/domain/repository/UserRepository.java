package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    User save(User user);

    void deleteById(String id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

}
