package app.project.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Service;
import app.project.entities.Village;


public interface VillageRepository extends JpaRepository<Village, Long> {

	List<Village> findAll();
	

		
}
