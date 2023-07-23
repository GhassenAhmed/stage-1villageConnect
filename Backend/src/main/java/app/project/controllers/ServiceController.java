package app.project.controllers;

import java.util.List;

import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Service;
import app.project.parametre.DataService;
import app.project.repositories.ServiceRepository;
import app.project.repositories.VillageRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ServiceController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	VillageRepository villageRepository;
	
	 @GetMapping("/getServiceRaiting")
	    public ResponseEntity<?> getServiceRaiting(
	    		@RequestParam(name = "search",defaultValue = "") String village,
	    		@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "per_page", defaultValue = "7") int size){
	    	
		 	
	    	Page<Service> service;
	    	if(village.isEmpty()){
	    		service=serviceRepository.getServiceRaiting(PageRequest.of(page, size));
	    		
	    	}else {
	    		service=serviceRepository.getServiceRaitingByVillage(village,PageRequest.of(page, size));
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
	 

}
