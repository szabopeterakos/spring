package com.peter.demo.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peter.demo.domain.Blogger;
import com.peter.demo.domain.Story;
import com.peter.demo.repository.BloggerRepo;
import com.peter.demo.repository.StoryRepo;

@Service
public class StoryService {

	StoryRepo myStoryRepo; // 5. a service egy repot kap
	BloggerRepo myBloggerRepo;
	
	
	@Autowired
	public void setMyBloggerRepo(BloggerRepo myBloggerRepo) {
		this.myBloggerRepo = myBloggerRepo;
	}

	@Autowired
	public void setMyStoryRepo(StoryRepo myStoryRepo) {
		this.myStoryRepo = myStoryRepo;
	}

	public List<Story> getStories() {
		
		return myStoryRepo.findAll(); // 6.aki viszont már csatlakozik a meglévő táblához a CRUD interfaceben megirt metódusaival
	}
	
	public Story getStory() {
		return myStoryRepo.findFirstByOrderByIdDesc();
	}
	
	
	@PostConstruct // a service singlton, igy az első objektum létrejöttekor példányszinten init() és többet nem -- automatikusan meghivódik 
	public void init() {
		// itt a heapüben hozom létre és dobom tovább az entitiyket
		Blogger blogger= new Blogger("Jimmy","Handrix");
		myBloggerRepo.save(blogger);
		Story story= new Story("Hendrix tépte a húrokat mi?",blogger);
		myStoryRepo.save(story);
	}

	public Story getbyID(String id) {
		Long integerID = Long.parseLong(id);
		Story currentStory = myStoryRepo.findById(integerID);
		boolean isNull = currentStory == null;
		
		if(isNull) {
			throw new IllegalArgumentException("Nem találtunk ilyen idvel story-t");
		}
		
		return currentStory;
	}

}
