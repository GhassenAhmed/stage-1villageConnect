package app.project.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Categorie;
import app.project.entities.Service;
import app.project.parametre.DataCategorie;
import app.project.repositories.CategorieRepository;
import app.project.repositories.ServiceRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CategoriesController {
	
	@Autowired
	CategorieRepository categorieRepository;
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@GetMapping("/getAllCategorie")
	public ResponseEntity<?> getAllCategories(@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "per_page", defaultValue = "5") int size){
		Page<Categorie> categories;
		
		categories=categorieRepository.findAll(PageRequest.of(page, size));
		
		int totale = categories.getTotalPages();
		int[] count_page = new int[totale];
		for (int i = 0; i < totale; i++) {
			count_page[i] = i;
		}
		DataCategorie data=new DataCategorie(count_page,categories,page);
		
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/getAll")
	public List<Categorie> getAll(){
		return categorieRepository.findAll();
	}
	
	@GetMapping("/countCategories")
  	public int countCategories(){	
  			return  categorieRepository.countCategories();	
  	}
	
	@PostMapping("/addCategorie")
	public  ResponseEntity<?> addCategorie(@RequestParam String name){
		int find=0;
		Categorie newCategorie = new Categorie();
		List<Categorie> categories = categorieRepository.getAllCategories();
		for(Categorie categorie : categories) {
			if(categorie.getCategorieName().equals(name)) {
				find=1;
			}
		}
		if(find==0) {
			newCategorie.setCategorieName(name);
			categorieRepository.save(newCategorie);
			return  ResponseEntity.ok().body("Categorie ajouter avec succes !");
		}else {
			return  ResponseEntity.ok().body("Nom utilisé !");
		}
	}
	
	 @DeleteMapping("/deleteCategorie")
	  public ResponseEntity<?> deleteService(@RequestParam("id") Long id){
		 List <Service> services = serviceRepository.getServiceCategorie(id);
		 for(int i= 0 ;i<services.size();i++) {
			 serviceRepository.deleteById(services.get(i).getId());
		 }
		 categorieRepository.deleteById(id);	
		  return  ResponseEntity.ok().body("Categorie supprimer");
	  }
	 
	

}
