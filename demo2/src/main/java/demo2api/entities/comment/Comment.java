package demo2api.entities.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
	@Size(max = 1000)
	public String text;
	
	public Long postTime;
	
	public long userid;
	
	public long postid;
	
	public Comment() {}

	public Comment(String t, Long pt) {
		text = t;
		postTime = pt;
	}
}