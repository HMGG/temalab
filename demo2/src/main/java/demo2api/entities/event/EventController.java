package demo2api.entities.event;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping("/events/nearby/{x}/{y}/{dist}")
	public Set<Event> getNearbyEvents(@PathVariable float x, @PathVariable float y, @PathVariable float dist){
		return eventService.getNearbyEvents(x,y,dist);
	}
	
	@RequestMapping("/events/upcoming/{time}/{x}/{y}/{dist}")
	public Set<Event> upcomingEvents(@PathVariable long time, @PathVariable float x, @PathVariable float y, @PathVariable float dist){
		Set<Event> ret = new HashSet<>();
		for(Event e : eventService.getNearbyEvents(x,y,dist)) 
			if(e.time<time)
				ret.add(e);
		return ret;
	}

}