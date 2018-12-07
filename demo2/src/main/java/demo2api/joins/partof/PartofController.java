package demo2api.joins.partof;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.conversation.Conversation;
import demo2api.entities.conversation.ConversationService;
import demo2api.entities.user.UserService;

@RestController
public class PartofController {

	@Autowired
	private PartofService partofService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConversationService conversationService;
	
	@RequestMapping("/conversations/{sid}")
	public Set<Conversation> getConversations(@PathVariable long sid){
		Set<Conversation> sc = new HashSet<>();
		for(Long l : partofService.getConversations(userService.getIdBySid(sid))) {
			sc.add(conversationService.getConversation(l));
		}
		return sc;
	}
	
	@RequestMapping("/conversations/{cid}/add/{uid}")
	public void addUserToConversation(@PathVariable long cid, @PathVariable long uid){
		partofService.addUserToConversation(new Partof(cid, uid));
	}
}