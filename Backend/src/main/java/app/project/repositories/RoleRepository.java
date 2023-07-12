package app.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
