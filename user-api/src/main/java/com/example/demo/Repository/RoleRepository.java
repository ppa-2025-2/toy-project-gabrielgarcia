package com.example.demo.Repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.repository.ListCrudRepository;

import com.example.demo.Repository.Entity.Role;

public interface RoleRepository extends ListCrudRepository<Role, Integer> {

    Role findByName(String name);

    Set<Role> findByNameIn(Collection<String> names);

    boolean existsByName(String name);
    
}
