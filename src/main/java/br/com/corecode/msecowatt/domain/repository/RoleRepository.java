package br.com.corecode.msecowatt.domain.repository;

import br.com.corecode.msecowatt.domain.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findById(String id);

    Optional<Role> findByName(String name);

    List<Role> findAll();

    Role save(Role role);
}
