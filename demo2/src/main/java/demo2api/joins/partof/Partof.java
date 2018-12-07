package demo2api.joins.partof;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(Parto.class)
@Table(name = "partof")
public class Partof {

	@Id
	public long conv;

	@Id
	public long person;
	
	public Partof() {
	}
	
	public Partof(long c, long p) {
		conv = c;
		person = p;
	}
}