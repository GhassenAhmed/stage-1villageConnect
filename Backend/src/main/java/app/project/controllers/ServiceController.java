package app.project.controllers;

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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ServiceController {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	 @GetMapping("/getServiceRaiting")
	    public ResponseEntity<?> getServiceRaiting(
	    		@RequestParam(name = "search",defaultValue = "") String village,
	    		@RequestParam(name = "page", defaultValue = "0") int page,
				@RequestParam(name = "per_page", defaultValue = "2") int size){
	    	
	    	Page<Service> service = null;
	    	if(village.isEmpty()) {
	    		service=serviceRepository.findAll(PageRequest.of(page, size));
	    	}
	    	int totale = service.getTotalPages();
			int[] count_page = new int[totale];
			for (int i = 0; i < totale; i++) {
				count_page[i] = i;
			}

			DataService data=new DataService(count_page,service,page);
			return ResponseEntity.ok(data);

	    }

}