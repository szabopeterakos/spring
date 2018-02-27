package com.petertailor.belsokonyveles.repository;


import com.petertailor.belsokonyveles.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRole extends CrudRepository<Role,Long> {
}
