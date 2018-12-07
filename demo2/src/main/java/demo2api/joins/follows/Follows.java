package demo2api.joins.follows;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Follow.class)
@Table(name = "follows")
public class Follows {

	@Id
	public long follower;
	
	@Id
	public long following;
	
	public Follows() {
	}
	
	public Follows(long fr, long fg) {
		follower = fr;
		following = fg;
	}
}

