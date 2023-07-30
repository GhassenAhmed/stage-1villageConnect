package app.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.entities.Notification;
import app.project.repositories.NotificationRepository;
import app.project.repositories.UserRepository;

@Service
public class NotificationService {

	@Autowired
	UserRepository UserRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationRepository notificationRepository;

		  public List<Notification> getNotif(long id){
			  List<Notification> notifs=notificationRepository.GetAllNotif(id);
			  return notifs;
		  }
		  
		  public List<Notification> getNotifNotSeenyet(long id){
			  List<Notification> notifs=notificationRepository.GetNotifNotSeen(id);
			  return notifs;
		  }
		  
		  public void DeleteById(long id) {
			  notificationRepository.deleteById(id);
		  }
		  
		  public void DeleteAllNotif(long id) {
			  notificationRepository.DeleteAllNotif(id);
		  }
		  
		  public void updateAllNotifUser(long id) {
			  notificationRepository.ChangerEtatNotif(id);
		  }
}
