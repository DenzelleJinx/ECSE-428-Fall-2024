package HouseIt.repository;

import org.springframework.data.repository.CrudRepository;

import HouseIt.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	Person findPersonByEmail(String email);

}