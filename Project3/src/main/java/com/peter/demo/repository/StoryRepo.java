package com.peter.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.peter.demo.domain.Story;

public interface StoryRepo extends CrudRepository<Story,Long> {

	List<Story> findAll(); //iterátort ad vissza de szűkithető
	
	// select * from stories limit 1; with chriteria A JPA nyelvén : 
	// automatizálja a sql lekérdezést JDBC re forditja aztán go sql 
	Story findFirstByOrderByIdDesc();

	Story findById(Long id);
}
