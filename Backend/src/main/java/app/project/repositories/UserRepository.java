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

	@Query(value="select count(*)  from user u,role r,user_role ur where u.id = ur.user_id  and ur.role_id=r.id and role_name='client'",nativeQuery=true)
	int CountUserClient();
	
	@Query(value="select count(*)  from user u,role r,user_role ur where u.id = ur.user_id  and ur.role_id=r.id and role_name='serviceProvider'",nativeQuery=true)
	int CountUserServiceProvider();
	
	@Query(value="select *  from user",nativeQuery=true)
	List<User> getAllUsers();
	
	@Query(value="select count(*) from user",nativeQuery=true)
	int countUsers();
	
	@Query(value="select * from user where password_token=:token",nativeQuery=true)
	User CheckToken(String token);
	
	@Query(value="select * from user where id=:id",nativeQuery=true)
	User getUserById(long id);
	
	

}
