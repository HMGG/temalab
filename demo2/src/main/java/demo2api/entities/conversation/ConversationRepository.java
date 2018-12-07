package demo2api.entities.conversation;

import org.springframework.data.repository.CrudRepository;

import demo2api.entities.conversation.Conversation;

public interface ConversationRepository extends CrudRepository<Conversation, Long>{}
