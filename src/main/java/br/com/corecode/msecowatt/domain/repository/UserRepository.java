package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findById(String id);

    boolean existsByUsername(String username);

    void deleteById(String id);

}
