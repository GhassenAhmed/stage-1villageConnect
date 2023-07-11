package app.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String username);

}
