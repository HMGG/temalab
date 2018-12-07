package demo2api.entities.conversation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.entities.conversation.ConversationRepository;

@Service
public class ConversationService {
	
	@Autowired
	private ConversationRepository conversationRepository;

	public Conversation getConversation(Long l) {
		return conversationRepository.findById(l).get();
	}

	public Conversation newConversation(Conversation c) {
		return conversationRepository.save(c);
	}
}