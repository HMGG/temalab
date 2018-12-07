package demo2api.entities.event;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long>{
	
	@Query("select e from Event e where (e.latitude>?1-?3 and e.latitude<?1+?3 and e.longitude>?2-?3 and e.longitude<?2+?3)")
    Set<Event> findWithin(float x, float y, float d);
}