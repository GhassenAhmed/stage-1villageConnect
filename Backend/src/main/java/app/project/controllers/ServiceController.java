package app.project.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Categorie;
import app.project.entities.Service;
import app.project.entities.User;
import app.project.entities.Village;
import app.project.parametre.DataService;
import app.project.repositories.CategorieRepository;
import app.project.repositories.ServiceRepository;
import app.project.repositories.VillageRepository;
import app.project.services.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ServiceController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	VillageRepository villageRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CategorieRepository categorieRepository;
	
	@Autowired
	VillageRepository VillageRepository;
	
	 @GetMapping("/getServiceRaiting")
	    public ResponseEntity<?> getServiceRaiting(
	    		@RequestParam(name = "id",defaultValue = "0") Long village_id,
	    		@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "per_page", defaultValue = "7") int size){
	    	
		 	
	    	Page<Service> service;
	    	if(village_id==0){
	    		service=serviceRepository.getServiceRaiting(PageRequest.of(page, size));
	    		
	    	}else {
	    		service=serviceRepository.getServiceRaitingByVillage(village_id,PageRequest.of(page, size));
	    	}
	    	int totale = service.getTotalPages();
			int[] count_page = new int[totale];
			for (int i = 0; i < totale; i++) {
				count_page[i] = i;
			}

			DataService data=new DataService(count_page,service,page);
			return ResponseEntity.ok(data);

	    }
	 
	 @GetMapping("/getServiceVerified")
	    public ResponseEntity<?> getServiceVerified(
	    		@RequestParam(name = "id",defaultValue = "0") Long village_id,
	    		@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "per_page", defaultValue = "7") int size){
	    	
		 	
	    	Page<Service> service;
	    	if(village_id==0){
	    		service=serviceRepository.getServiceVerified(PageRequest.of(page, size));
	    		
	    	}else {
	    		service=serviceRepository.getServiceVerifiedByVillage(village_id,PageRequest.of(page, size));
	    	}
	    	int totale = service.getTotalPages();
			int[] count_page = new int[totale];
			for (int i = 0; i < totale; i++) {
				count_page[i] = i;
			}

			DataService data=new DataService(count_page,service,page);
			return ResponseEntity.ok(data);

	    }
	 
	 @GetMapping("/getServiceCategorie")
	 public ResponseEntity<?> getServiceCategorie(@RequestParam("id") Long id){
		 List<Service> services = serviceRepository.getServiceCategorie(id);
		 if(services.size()==0) {
			 return ResponseEntity.ok(false);
		 }
		 return ResponseEntity.ok(services);
	 }
	 
	 @PostMapping("/createService")
	 public ResponseEntity<?> createService(HttpServletRequest request,@RequestBody Service service,@RequestParam("categorie_id") Long categorie_id,@RequestParam("village_id") Long village_id){
		 User user = userService.UserAuth(request);
		 Categorie categorie=categorieRepository.findCategorieById(categorie_id);
		 Village village = villageRepository.findVillageById(village_id);
		 Service newService=new Service();
		 newService.setServiceName(service.getServiceName());
		 newService.setDescription(service.getDescription());
		 newService.setAdresse(service.getAdresse());
		 newService.setMinPrice(service.getMinPrice());
		 newService.setMaxPrice(service.getMaxPrice());
		 newService.setThumbnailUrl(service.getThumbnailUrl());
		 newService.setYearsInBusiness(service.getYearsInBusiness());
		 newService.setPhoto(service.getPhoto());
		 newService.setCategorie(categorie);
		 newService.setVillage(village);
		 newService.setUser(user);
		 serviceRepository.save(newService);
		 return new ResponseEntity<>(newService,HttpStatus.OK);
	 }
	 

}
