package app.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Role;
import app.project.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="select * from user where email=:email",nativeQuery=true)
	Optional<User> getUserByemail(String email);
	
	
	@Query(value="select * from user where email=:email",nativeQuery=true)
	User getUserByEmail2(String email);
	
	
	@Query(value="select status from user_role where user_id=:id limit 1",nativeQuery=true)
	int GetStatus(long id);
	
	@Query(value="select u.* from user u,role r,user_role ur where r.id=ur.role_id and ur.user_id=u.id and r.role_name='admin';",nativeQuery=true)
	User GetAdmin();
	
	

}
