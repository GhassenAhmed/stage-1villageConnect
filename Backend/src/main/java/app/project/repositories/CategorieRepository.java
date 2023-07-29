package app.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Categorie;
import app.project.entities.Role;

public interface CategorieRepository  extends JpaRepository<Categorie, Long>{
	
	@Query(value="select * from Categorie",nativeQuery=true)
	List<Categorie> getAllCategories();
	
	
	@Query(value="select * from Categorie where id=:id",nativeQuery=true)
     Categorie findCategorieById(Long id);
	

}
