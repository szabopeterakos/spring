package com.petertailor.belsokonyveles.repository;


import com.petertailor.belsokonyveles.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUser extends CrudRepository<User,Long> {

    User findByEmail(String email);

}
