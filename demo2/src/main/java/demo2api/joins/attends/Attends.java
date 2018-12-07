package demo2api.joins.attends;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Attend.class)
@Table(name = "attends")
public class Attends {

	@Id
	public long event;

	@Id
	public long person;
	
	public Attends() {
	}
	
	public Attends(long e, long p) {
		event = e;
		person = p;
	}
	
	
}
