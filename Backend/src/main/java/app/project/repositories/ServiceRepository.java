package app.project.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.project.entities.Service;




@EnableJpaRepositories
public interface ServiceRepository  extends JpaRepository<Service, Long>{

	
	

	
	
}
