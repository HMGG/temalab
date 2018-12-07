package demo2api.joins.follows;

import java.io.Serializable;

public class Follow implements Serializable{

	public Follow(long id1, long id2) {
		follower = id1;
		following = id2;
	}
	
	public Follow() {}
	
	private static final long serialVersionUID = 1L;

	public long follower;

	public long following;
}
