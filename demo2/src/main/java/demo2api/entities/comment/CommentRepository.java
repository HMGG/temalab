package demo2api.entities.comment;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import demo2api.entities.comment.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

	public Set<Comment> findByPostid(long id);
	
}