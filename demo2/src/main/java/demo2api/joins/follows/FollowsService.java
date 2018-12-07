package demo2api.joins.follows;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.joins.follows.FollowsRepository;

@Service
public class FollowsService {
	
	@Autowired
	private FollowsRepository followsRepository;

	public void follow(long id1, long id2) {
		followsRepository.save(new Follows(id1, id2));
	}
	
	public Set<Long> getFollowing(long id) {
		return followsRepository.findFollowingByFollower(id);
	}
	
	public Set<Long> getFollowers(long id) {
		return followsRepository.findFollowersByFollowing(id);
	}

	public void unfollow(long id1, long id2) {
		followsRepository.delete(new Follows(id1, id2));
	}

	public boolean doesFollow(long id1, long id2) {
		return followsRepository.existsById(new Follow(id1, id2));
	}
}