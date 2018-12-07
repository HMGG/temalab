package demo2api.joins.attends;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.event.Event;
import demo2api.entities.event.EventService;
import demo2api.entities.user.User;
import demo2api.entities.user.UserService;


@RestController
public class AttendsController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AttendsService attendsService;
	
	@RequestMapping(value = "/events/{sid}", method = RequestMethod.POST)
	public long newEvent(@RequestBody Event e, @PathVariable long sid) {
		e.creatorid=userService.getIdBySid(sid);
		long ret = eventService.save(e);
		attendsService.attend(new Attends(ret, e.creatorid));
		return ret;
	}
	
	@RequestMapping(value = "/events/{eid}/{sid}", method = RequestMethod.PUT)
	public void attendevent(@PathVariable long sid, @PathVariable long eid){
		attendsService.attend(new Attends(eid, userService.getIdBySid(sid)));
	}
	
	@RequestMapping(value = "/events/{eid}/{sid}", method = RequestMethod.DELETE)
	public void unattendevent(@PathVariable long sid, @PathVariable long eid) {
		attendsService.unattend(new Attends(eid, userService.getIdBySid(sid)));
	}
	
	@RequestMapping("/events/{sid}")
	public Set<Event> myevents(@PathVariable long sid) {
		Set<Event> ret = new HashSet<>();
		for(Long l : attendsService.getUsersEvents(userService.getIdBySid(sid)))
			ret.add(eventService.getById(l));
		return ret;
	}
	
	@RequestMapping("/events/{id}")
	public Set<User> attendees(@PathVariable long id) {
		Set<User> ret = new HashSet<>();
		for(Long l : attendsService.getAttendees(id))
			ret.add(userService.getUser(l));
		return ret;
	}
}