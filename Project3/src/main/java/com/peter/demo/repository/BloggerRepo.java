package com.peter.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.peter.demo.domain.Blogger;
import com.peter.demo.domain.Story;

public interface BloggerRepo extends CrudRepository<Blogger,Long> {

	// select * from stories
	List<Blogger> findAll(); //iterátort ad vissza de szűkithető
	
}
