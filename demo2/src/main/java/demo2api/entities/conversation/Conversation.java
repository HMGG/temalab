package demo2api.entities.conversation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "convs")
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@Size(max = 1000)
	public String title;
	
	public Conversation() {}

	public Conversation(String n) {
		title = n;
	}
}