package demo2api.joins.partof;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import demo2api.joins.partof.Partof;

public interface PartofRepository extends CrudRepository<Partof, Parto>{

	@Query("SELECT p.conv FROM Partof p WHERE p.person=?1")
	Set<Long> findConverstionsByUser(long id);
	
}