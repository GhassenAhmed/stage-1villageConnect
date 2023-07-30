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
	
	@Query(value="select * from service where village_id=:village_id order by raiting desc",nativeQuery=true)
	Page<Service> getServiceRaitingByVillage(Long village_id,Pageable pageable);
	
	@Query(value="select * from service where is_background_verified=true",nativeQuery=true)
	Page<Service> getServiceVerified(Pageable pageable );
	
	
	@Query(value="select * from service where  village_id=:village_id and is_background_verified=1",nativeQuery=true)
	Page<Service> getServiceVerifiedByVillage(Long village_id,Pageable pageable);

	@Query(value="select count(*) from service where user_id=:id",nativeQuery=true)
	List<Service> getServiceByUserId(Long id);
	
}
