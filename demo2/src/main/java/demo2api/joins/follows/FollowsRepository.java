package demo2api.joins.follows;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import demo2api.joins.follows.Follows;

public interface FollowsRepository extends CrudRepository<Follows, Follow>{

	@Query("SELECT f.following FROM Follows f WHERE f.follower=?1")
	Set<Long> findFollowingByFollower(long id);
	
	@Query("SELECT f.follower FROM Follows f WHERE f.following=?1")
	Set<Long> findFollowersByFollowing(long id);
	
}
