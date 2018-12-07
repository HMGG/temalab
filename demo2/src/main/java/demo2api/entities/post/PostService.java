package demo2api.entities.post;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public Set<Post> getPosts(long id) {
		Set<Post> sp = postRepository.getByUserid(id);
		for(Post p : sp)
			p.userid=0;
		return sp;
	}

	public long newPost(Post p) {
		return postRepository.save(p).id;
	}
	
	public Post getPost(long id) {
		return postRepository.findById(id).get();
	}
	
	public Set<Post> getNearbyPosts(float x, float y, float d){
		return postRepository.findWithin(x,y,d);
	}

	public Set<Post> findRecentOfUser(long time, long id) {
		return postRepository.findRecentOfUser(time, id);
	}
	
/*	public public Set<Post> getRecentPosts(Time time){
		return postRepository.findPosts
	}*/
}