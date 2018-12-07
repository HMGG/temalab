package demo2api.joins.partof;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.joins.partof.PartofRepository;

@Service
public class PartofService {
	
	@Autowired
	private PartofRepository partofRepository;

	public Set<Long> getConversations(long id) {
		return partofRepository.findConverstionsByUser(id);
	}

	public void addUserToConversation(Partof partof) {
		partofRepository.save(partof);
	}

	public boolean hasConversation(Partof partof) {
		return partofRepository.existsById(new Parto(partof));
	}
}
