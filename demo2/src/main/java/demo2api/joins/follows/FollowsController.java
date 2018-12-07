package demo2api.joins.follows;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.user.User;
import demo2api.entities.user.UserService;

@RestController
public class FollowsController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowsService followsService;
	
	@RequestMapping("{sid}/follow/{id}")
	public void followUser(@PathVariable long sid, @PathVariable long id) {
		followsService.follow(userService.getIdBySid(sid), id);
	}
	
	@RequestMapping("{sid}/unfollow/{id}")
	public void unfollowUser(@PathVariable long sid, @PathVariable long id) {
		followsService.unfollow(userService.getIdBySid(sid), id);
	}
	
	@RequestMapping("{id}/followers")
	public Set<User> getFollowers(@PathVariable long id){
		Set<User> ret = new HashSet<>();
		for(Long l : followsService.getFollowers(id)) {
			User u = userService.getUser(l);
			u.hideinfo();
			ret.add(u);
		}
		return ret;
	}
	
	@RequestMapping("{id}/following")
	public Set<User> getFollowing(@PathVariable long id){
		Set<User> ret = new HashSet<>();
		for(Long l : followsService.getFollowing(id)) {
			User u = userService.getUser(l);
			u.hideinfo();
			ret.add(u);
		}
		return ret;
	}
}
