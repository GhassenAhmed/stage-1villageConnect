package app.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.Inbox;
import app.project.entities.User;
import app.project.repositories.InboxRepository;
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
	
	
	
	@PostMapping("/AddMessagePrivate")
    public ResponseEntity<?> AddMessage(@RequestParam("id")long user_id ,@RequestParam("message")String message,HttpServletRequest request){
    	 User userAuth=userService.UserAuth(request);
    	 Inbox chat=new Inbox();
    	 chat.setUserEnvoi(userAuth);
    	 chat.setUserRecu(userRepository.getUserById(user_id));
    	 chat.setMessage(message);
    	 InboxRepository.save(chat);
    	return ResponseEntity.ok("add message");
    }
	
	@GetMapping("/AllChatPrivate")
    public ResponseEntity<?> AllChatPrivate(@RequestParam("idrecu")long id,HttpServletRequest request){
    	 User userAuth=userService.UserAuth(request);
    	 List<Inbox> chats=InboxRepository.getMessageprivate(userAuth.getId(), id);
    	 return ResponseEntity.ok(chats);
    }
	
	

}
