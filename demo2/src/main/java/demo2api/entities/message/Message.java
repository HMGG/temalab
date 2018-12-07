package demo2api.entities.message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@Size(max = 1000)
	public String text;
	
	public Long time;

	public long convid;
	
	public long userid;
	
	public Message() {}

	public Message(String t, Long pt) {
		text = t;
		time = pt;
	}
}