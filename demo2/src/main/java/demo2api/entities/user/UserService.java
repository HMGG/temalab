package demo2api.entities.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.Location;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Set<User> getAllUsers() {
		Set<User> ret = new HashSet<>();
		for(User u : userRepository.findAll()) {
			ret.add(u);
		}
		return ret;
	}
	
	public User getUser(long id) {
		return userRepository.findById(id).get().hideinfo();
	}

	public long addUser(User u) {
		if(userRepository.existsByEmail(u.email))
			return 0;
		else 
			return userRepository.save(u).id;
	}

	public void updateUser(User nu, long sid) {
		User u = getUserBySid(sid);
		u.bio = nu.bio;
		u.birthday = nu.birthday;
		u.name = nu.name;
		u.password = nu.password;
		userRepository.save(u);
	}

	public User getUserBySid(long sid) {
		return userRepository.findUserBySessionId(sid).hideinfo();
	}
	
	public Long getIdBySid(long sid) {
		return userRepository.findIdBySid(sid);
	}
	
	public Set<User> getNearbyUsers(float x, float y, float d){
		Set<User> su = userRepository.findWithin(x,y,d);
		for(User u : su)
			u.hideinfo();
		return su;
	}

	public long login(LoginData ld) {
		User u = userRepository.getUserByEmailAndPassword(ld.email, ld.password);
		if(u!=null) {
			u.sessionId=ld.sid;
			userRepository.save(u);
			return u.id;
		}
		else
			return 0;

	}

	public void logout(long sid) {
		userRepository.logout(sid);
	}

	public void updateUserLocation(Location l, long sid) {
		User u = getUserBySid(sid);
		u.latitude = l.latitude;
		u.longitude = l.longitude;
		userRepository.save(u);
	}


	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

}
