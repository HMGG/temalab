package demo2api.entities.user;
    
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo2api.Location;
import demo2api.joins.follows.FollowsService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowsService followsService;
	
	//FOR DEBUG
	@RequestMapping("/users")
	public Set<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@RequestMapping("/user/{id}")
	public User getUser(@PathVariable long id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public long addUser(@RequestBody User u) {
		return userService.addUser(u);
	}
	
	@RequestMapping(value = "/edit/{sid}", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User nu, @PathVariable long sid) {
		userService.updateUser(nu, sid);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.PUT)
	public long updateSessionId(@RequestBody LoginData ld) {
		return userService.login(ld);
	}
	
	@RequestMapping(value = "/logout/{sid}")
	public void deleteSessionId(@PathVariable long sid) {
		userService.logout(sid);
	}
	
	@RequestMapping("/users/nearby/{x}/{y}/{dist}")
	public Set<User> getNearbyUsers(@PathVariable float x, @PathVariable float y, @PathVariable float dist){
		return userService.getNearbyUsers(x,y,dist);
	}
	
	@RequestMapping("/users/nearby/{x}/{y}/{dist}/{sid}")
	public Set<User> getNearbyFollowing(@PathVariable float x, @PathVariable float y, @PathVariable float dist, @PathVariable long sid){
		Set<User> nearby = userService.getNearbyUsers(x,y,dist);
		long id = userService.getIdBySid(sid);
		for(User u : nearby)
			if(!followsService.doesFollow(id, u.id))
				nearby.remove(u);
		return nearby;
	}
	
	@RequestMapping(value = "/user/{sid}/locate", method = RequestMethod.POST)
	public void locateUser(@PathVariable long sid, @RequestBody Location l) {
		userService.updateUserLocation(l, sid);
	}

	//FOR DEBUG
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
}
