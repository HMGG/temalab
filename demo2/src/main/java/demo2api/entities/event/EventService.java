package demo2api.entities.event;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	public long save(Event e) {
		return eventRepository.save(e).id;
	}

	public Event getById(Long l) {
		return eventRepository.findById(l).get();
	}
	
	public Set<Event> getNearbyEvents(float x, float y, float d){
		return eventRepository.findWithin(x,y,d);

	}
}
