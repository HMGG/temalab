package demo2api.entities.post;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.user.UserService;
import demo2api.joins.follows.FollowsService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private FollowsService followsService;
	
	@RequestMapping("/feed/{sid}")
	public Set<Post> getPosts(@PathVariable long sid) {
		Set<Post> sp = new HashSet<>();
		Set<Long> sl = followsService.getFollowing(userService.getIdBySid(sid));
		for(Long l : sl) {
			sp.addAll(postService.getPosts(l));
		}
		return sp;
	}
	
	@RequestMapping(value = "/feed/{sid}", method = RequestMethod.POST)
	public long newPost(@RequestBody Post p, @PathVariable long sid) {
		p.userid = userService.getIdBySid(sid);
		return postService.newPost(p);
	}
	
	@RequestMapping("/feed/nearby/{sid}/{x}/{y}/{dist}")
	public Set<Post> getNearbyPosts(@PathVariable long sid, @PathVariable float x, @PathVariable float y, @PathVariable float dist){
		Set<Post> ret = new HashSet<>();
		Set<Long> f = followsService.getFollowing(userService.getIdBySid(sid));
		for(Post p : postService.getNearbyPosts(x,y,dist))
			if(f.contains(p.userid))
				ret.add(p);
		return ret;
	}
	
	@RequestMapping("/feed/recent/{time}/{sid}")
	public Set<Post> upcomingEvents(@PathVariable long time, @PathVariable long sid){
		Set<Post> ret = new HashSet<>();
		Set<Long> f = followsService.getFollowing(userService.getIdBySid(sid));
		for(Long l : f)
			ret.addAll(postService.findRecentOfUser(time, l));
		return ret;
	}
	
	@RequestMapping("/post/{postid}")
	public Post getPost(@PathVariable long postid){
		return postService.getPost(postid);
	}

}