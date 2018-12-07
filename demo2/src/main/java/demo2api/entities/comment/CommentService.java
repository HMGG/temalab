package demo2api.entities.comment;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.entities.comment.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	public long newComment(Comment c) {
		Comment cm = commentRepository.save(c);
		return cm.id;
	}

	public void removeComment(long id, long l) {
		if(commentRepository.findById(id).get().userid==l)
			commentRepository.deleteById(id);
	}
	
	public Set<Comment> getByPostId(long id) {
		return commentRepository.findByPostid(id);
	}
}