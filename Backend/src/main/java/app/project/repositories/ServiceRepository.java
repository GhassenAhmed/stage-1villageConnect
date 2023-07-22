package app.project.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;

import app.project.entities.Service;
import app.project.entities.User;




@EnableJpaRepositories
public interface ServiceRepository  extends JpaRepository<Service, Long>{

	@Query(value="select * from service where categorie_id=:id",nativeQuery=true)
	List<Service> getServiceCategorie(Long id);
	

	
	
}
