package app.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Role;
import app.project.entities.User;

public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query(value="select * from Role where role_name='client' ",nativeQuery=true)
	List<Role> getRoleClient();
}
