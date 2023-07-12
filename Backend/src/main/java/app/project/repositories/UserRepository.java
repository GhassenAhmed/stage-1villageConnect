package app.project.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.User;


public interface UserRepository extends JpaRepository<User, Long>{

	@Query(value="select * from user where email=:email",nativeQuery=true)
	Optional<User> getUserByemail(String email);
	
	
	@Query(value="select * from user where email=:email",nativeQuery=true)
	User getUserByEmail2(String email);
	
	
	@Query(value="select status from user_role where user_id=:id limit 1",nativeQuery=true)
	int GetStatus(long id);
	

}
