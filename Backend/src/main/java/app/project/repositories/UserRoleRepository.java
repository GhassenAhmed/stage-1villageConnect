package app.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
