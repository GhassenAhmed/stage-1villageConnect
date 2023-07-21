package app.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.Village;


public interface VillageRepository extends JpaRepository<Village, Long> {

	List<Village> findAll();
	
	

		
}
