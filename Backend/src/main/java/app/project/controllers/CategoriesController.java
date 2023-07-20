package app.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Categorie;
import app.project.repositories.CategorieRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CategoriesController {
	
	@Autowired
	CategorieRepository categorieRepository;
	
	
	
	@GetMapping("/getAllCategorie")
	public List<Categorie> getAllCategories(){
		return categorieRepository.getAllCategories();
	}

}
