package demo2api.entities.like;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;
	
//	@ManyToOne
	public long userid;
	
//	@ManyToOne
	public long postid;
	
	public Like() {}

	public Like(long u, long p) {
		userid = u;
		postid = p;
	}
}
