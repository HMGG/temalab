package demo2api.entities.post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import demo2api.Location;

@Entity
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@Size(max = 1000)
	public String text;
	
	public Long postTime;
	
	public Float latitude;

	public Float longitude;
	
	public long userid;
	
	public Post() {}

	public Post(String t, Long pt, Location l) {
		text = t;
		postTime = pt;
		latitude = l.latitude;
		longitude = l.longitude;
	}
}