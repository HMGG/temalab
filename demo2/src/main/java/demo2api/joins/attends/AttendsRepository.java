package demo2api.joins.attends;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import demo2api.joins.attends.Attends;

public interface AttendsRepository extends CrudRepository<Attends, Attend>{

	Set<Long> findAllByPerson(long id);

	Set<Long> findAllByEvent(long id);
}