package app.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Village;
import app.project.repositories.VillageRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class VillageController {
	
	@Autowired
	VillageRepository villageRepository;
	
	
	@GetMapping("/getAllVillages")
	public List<Village> getAllVillages(){
		return villageRepository.findAll();
	}

	
	 @GetMapping("/countVillages")
	  	public int countVillages(){	
	  			return  villageRepository.countVillages();	
	  	}
}
