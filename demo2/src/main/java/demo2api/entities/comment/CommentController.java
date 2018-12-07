package demo2api.entities.comment;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo2api.entities.user.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/post/{postid}/{sid}", method = RequestMethod.POST)
	public long newComment(@RequestBody Comment c, @PathVariable long postid, @PathVariable long sid) {
		c.userid = userService.getIdBySid(sid);
		c.postid = postid;
		return commentService.newComment(c);
	}
	
	@RequestMapping(value = "/post/{postid}/{id}/{sid}", method = RequestMethod.DELETE)
	public void removeComment(@RequestParam long id, @RequestParam long sid) {
		commentService.removeComment(id, userService.getIdBySid(sid));
	}
	
	@RequestMapping("/post/{postid}/comments")
	public Set<Comment> getComments(@PathVariable long postid){
		return commentService.getByPostId(postid);
	}
	
	

}