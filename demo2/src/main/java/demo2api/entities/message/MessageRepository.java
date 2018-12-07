package demo2api.entities.message;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import demo2api.entities.message.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

	public Set<Message> findByConvid(long id);
	
}