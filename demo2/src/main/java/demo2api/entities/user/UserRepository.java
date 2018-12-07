package demo2api.entities.user;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long>{
	
	public boolean existsByEmail(String email);

	public User findUserBySessionId(long sid);
	
	@Query("select u from User u where (u.latitude>?1-?3 and u.latitude<?1+?3 and u.longitude>?2-?3 and u.longitude<?2+?3)")
    Set<User> findWithin(float x, float y, float d);

	@Query("select u from User u where (u.email=?1 and u.password=?2)")
	public User getUserByEmailAndPassword(String email, String password);

	@Query("select u.id from User u where u.sessionId = ?1")
	public Long findIdBySid(long sid);
	
	@Modifying
	@Transactional
	@Query("update User set sessionId = null where sessionId = ?1")
	public void logout(long sid);
	
}
