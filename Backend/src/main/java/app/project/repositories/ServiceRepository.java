package app.project.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import app.project.entities.Service;






public interface ServiceRepository  extends JpaRepository<Service, Long>{

	@Query(value="select * from service where categorie_id=:id and status=1",nativeQuery=true)
	List<Service> getServiceCategorie(Long id);
	
	@Query(value="select * from service where status=1 order by raiting desc",nativeQuery=true)
	Page<Service> getServiceRaiting(Pageable pageable );
	
	@Query(value="select * from service where status=1 and village_id=:village_id order by raiting desc",nativeQuery=true)
	Page<Service> getServiceRaitingByVillage(Long village_id,Pageable pageable);
	
	@Query(value="select * from service where status=1 and is_background_verified=true",nativeQuery=true)
	Page<Service> getServiceVerified(Pageable pageable );
	
	
	@Query(value="select * from service where  status=1 and village_id=:village_id and is_background_verified=1",nativeQuery=true)
	Page<Service> getServiceVerifiedByVillage(Long village_id,Pageable pageable);

	@Query(value="select count(*) from service where status=1 and user_id=:id",nativeQuery=true)
	List<Service> getServiceByUserId(Long id);
	
	@Query(value="select count(*) from service where is_background_verified=1",nativeQuery=true)
	int getServiceVerified();
	
	@Query(value="select count(*) from service where is_background_verified=0",nativeQuery=true)
	int getServiceNonVerified();
	
	@Query(value="select * from service where status=0",nativeQuery=true)
	List<Service> getServiceNotActived();
	
	@Query(value="select * from service where id=:id",nativeQuery=true)
	Service getServiceByid(Long id);
	
	@Query(value="select count(*) from service where status=1",nativeQuery=true)
	int countServices();
	
	@Query(value="select * from service where categorie_id=:id",nativeQuery=true)
	Service getServiceByCategorieId(Long id);
	
	@Query(value="select * from service where status=1",nativeQuery=true)
	List<Service> getAllService();
}
