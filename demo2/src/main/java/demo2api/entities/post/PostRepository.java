package demo2api.entities.post;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long>{

	Set<Post> getByUserid(long id);
	
	@Query("select p from Post p where (p.latitude>?1-?3 and p.latitude<?1+?3 and p.longitude>?2-?3 and p.longitude<?2+?3)")
    Set<Post> findWithin(float x, float y, float d);

	@Query("select p from Post p where (p.postTime>?1 and p.userid=?2)")
	Set<Post> findRecentOfUser(long time, long id);
	
}