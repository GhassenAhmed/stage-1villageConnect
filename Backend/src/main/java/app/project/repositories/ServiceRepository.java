package app.project.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import app.project.entities.Service;






public interface ServiceRepository  extends JpaRepository<Service, Long>{

	@Query(value="select * from service where categorie_id=:id",nativeQuery=true)
	List<Service> getServiceCategorie(Long id);
	
	@Query(value="select * from service order by raiting desc",nativeQuery=true)
	Page<Service> getServiceRaiting(Pageable pageable );
	
	@Query(value="select * from service s,village v where s.id=v.id and v.village_name LIKE ? order by s.raiting desc",nativeQuery=true)
	Page<Service> getServiceRaitingByVillage(String village,Pageable pageable);
	
	
	
	
	

	
	
}
