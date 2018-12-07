package demo2api.entities.conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.user.UserService;
import demo2api.joins.partof.Partof;
import demo2api.joins.partof.PartofService;

@RestController
public class ConversationController {
	
	@Autowired
	private ConversationService conversationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PartofService partofService;
	
	@RequestMapping(value = "/conversations/{sid}", method = RequestMethod.POST)
	public Conversation newConversation(@PathVariable long sid, @RequestBody Conversation c){
		Conversation cn = conversationService.newConversation(c);
		partofService.addUserToConversation(new Partof(c.id, userService.getIdBySid(sid)));
		return cn;
	}
	
	
}