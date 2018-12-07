package demo2api.entities.message;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.entities.message.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;

	public Set<Message> getByConvId(long id) {
		return messageRepository.findByConvid(id);
	}

	public void newMessage(Message m) {
		messageRepository.save(m);
	}
}