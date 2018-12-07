package demo2api.entities.message;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.message.MessageService;
import demo2api.entities.user.UserService;
import demo2api.joins.partof.Partof;
import demo2api.joins.partof.PartofService;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private PartofService partofService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/conversations/{sid}/{id}")
	public Set<Message> getConversation(@PathVariable long id, @PathVariable long sid){
		if(partofService.hasConversation(new Partof(id, userService.getIdBySid(sid))))
			return messageService.getByConvId(id);
		else
			return null;
	}
	
	@RequestMapping(value = "/conversations/{sid}/{id}/", method = RequestMethod.POST)
	public void newMessage(@PathVariable long id, @PathVariable long sid, @RequestBody Message m){
		long userid = userService.getIdBySid(sid);
		if(partofService.hasConversation(new Partof(id, userid))) {
			m.convid = id;
			m.userid = userid;
			messageService.newMessage(m);
		}
			
	}
}