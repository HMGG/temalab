package demo2api.joins.attends;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo2api.joins.attends.AttendsRepository;

@Service
public class AttendsService {
	
	@Autowired
	private AttendsRepository attendsRepository;

	public void attend(Attends attends) {
		attendsRepository.save(attends);
		
	}

	public void unattend(Attends attends) {
		attendsRepository.delete(attends);
		
	}

	public Set<Long> getUsersEvents(long id) {
		return attendsRepository.findAllByPerson(id);
	}

	public Set<Long> getAttendees(long id) {
		return attendsRepository.findAllByEvent(id);
	}
}