package HouseIt.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import HouseIt.model.Person;
import HouseIt.model.Renter;

@SpringBootTest
public class PersonRepositoryTests {
	@Autowired
	private PersonRepository personRepository;

	@AfterEach
	public void clearDatabase() {
		personRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadPerson() {
		// TODO
		assertTrue(true);
	}
}