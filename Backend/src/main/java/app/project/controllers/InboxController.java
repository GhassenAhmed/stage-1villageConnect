package app.project.controllers;

import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Inbox;
import app.project.entities.Notification;
import app.project.entities.User;
import app.project.repositories.InboxRepository;
import app.project.repositories.NotificationRepository;
import app.project.repositories.UserRepository;
import app.project.services.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class InboxController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	InboxRepository InboxRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	
	
	
	@PostMapping("/AddMessagePrivate")
    public ResponseEntity<?> AddMessage(@RequestParam("id")long user_id ,@RequestParam("message")String message,HttpServletRequest request){
    	 User userAuth=userService.UserAuth(request);
    	 Inbox chat=new Inbox();
    	 chat.setUserEnvoi(userAuth);
    	 chat.setUserRecu(userRepository.getUserById(user_id));
    	 chat.setMessage(message);
    	 Notification notif = new Notification();
    	 notif.setEtat(0);
    	 notif.setMessage("Vous avez un message !");
    	 notif.setUserEnvoi(userAuth);
    	 notif.setUserRecu(userRepository.getUserById(user_id));
    	 notificationRepository.save(notif);
    	 InboxRepository.save(chat);
    	return ResponseEntity.ok("add message");
    }
	
	@GetMapping("/AllChatPrivate")
    public ResponseEntity<?> AllChatPrivate(@RequestParam("idrecu")long id,HttpServletRequest request){
    	 User userAuth=userService.UserAuth(request);
    	 List<Inbox> chats=InboxRepository.getMessageprivate(userAuth.getId(), id);
    	 return ResponseEntity.ok(chats);
    }
	
	@GetMapping("/getAmis")
	public ResponseEntity<?>getAmis(HttpServletRequest request){
		User userAuth=userService.UserAuth(request);
		 List<User> amis= userRepository.getAmis(userAuth.getId());
		 return ResponseEntity.ok(amis);
	}
		
	}
	
