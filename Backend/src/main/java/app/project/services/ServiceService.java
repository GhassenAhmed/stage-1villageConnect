package app.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.repositories.ServiceRepository;

@Service
public class ServiceService {

	@Autowired
	ServiceRepository serviceRepository;
	
	public int getServiceVerified() {
		return serviceRepository.getServiceVerified();
	}
	
	public int getServiceNonVerified() {
		return serviceRepository.getServiceNonVerified();
	}
	
}
