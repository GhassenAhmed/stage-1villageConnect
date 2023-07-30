package app.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Notification;
import app.project.entities.User;
import app.project.repositories.NotificationRepository;
import app.project.services.NotificationService;
import app.project.services.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class NotificationController {

	@Autowired
	NotificationRepository notificationRepostory;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	
	
	@GetMapping("/GetNotif")
    public ResponseEntity<?> getallNotif(HttpServletRequest request){
    	  User userAuth=userService.UserAuth(request);
    	  List<Notification> notif=notificationService.getNotif(userAuth.getId());
    	  return  ResponseEntity.ok().body(notif);
    }
	
	 @GetMapping("/GetNotifNotSeen")
	    public ResponseEntity<?> GetNotifNotSeen(HttpServletRequest request){
	    	  User userAuth=userService.UserAuth(request);
	    	  List<Notification> notif=notificationService.getNotifNotSeenyet(userAuth.getId());
	    	  return  ResponseEntity.ok().body(notif);
	    }
	    
	    @DeleteMapping("/DeleteNotificationById")
	    public ResponseEntity<?> DeleteNotifById(@RequestParam("id") long id){
	    	notificationService.DeleteById(id);
	    	 return  ResponseEntity.ok().body("delete with success");
	    }
	    
	    @DeleteMapping("/DeleteAllNotif")
	    public ResponseEntity<?> DeleteAll(HttpServletRequest request){
	    	  User userAuth=userService.UserAuth(request);
	    	  notificationService.DeleteAllNotif(userAuth.getId());
	    	 return  ResponseEntity.ok().body("Delete success");
	    }
	    
	    @PostMapping("/updateNotif")
	    public ResponseEntity<?> updateNotif(HttpServletRequest request){
	    	  User userAuth=userService.UserAuth(request);
	    	  notificationService.updateAllNotifUser(userAuth.getId());
	    	  return  ResponseEntity.ok().body("update Notif With Success");
	    }
	
}
