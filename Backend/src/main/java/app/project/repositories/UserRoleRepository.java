package app.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
	
	@Query(value="select * from user_role where user_id=:id and role_id=1 and status=0",nativeQuery=true)
	UserRole getUserRole(long id);
	
	@Query(value="select * from user_role where user_id=:id",nativeQuery=true)
	List<UserRole> getUserRoles(long id);
	
	@Query(value="delete from user_role where user_id=:id",nativeQuery=true)
	UserRole deleteUserRole(long id);
}
