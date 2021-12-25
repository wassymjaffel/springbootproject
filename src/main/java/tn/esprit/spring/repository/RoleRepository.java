package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
