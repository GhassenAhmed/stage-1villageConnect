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
	
	@Query(value="select * from Role where role_name='serviceProvider' ",nativeQuery=true)
     Role getRoleServiceProvider();
	
	@Query(value="INSERT INTO user_role (user_id, role_id, status) VALUES (:id, 3, 1)",nativeQuery=true)
	void  insertRole(Long id);
	
	@Query(value="select count(*) from user_role where user_id=:id and role_id=3",nativeQuery=true)
	int  getRoleService(Long id);
	
	
}
