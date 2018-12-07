package demo2api.entities.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import demo2api.Location;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@Size(max = 250)
	public String title;
	
	public Long time;
	
	public Float latitude;
	
	public Float longitude;
	
	public long creatorid;
	
	public Event() {}

	public Event(String n, Long t, Location l) {
		title = n;
		time = t;
		latitude = l.latitude;
		longitude = l.longitude;
	}

}