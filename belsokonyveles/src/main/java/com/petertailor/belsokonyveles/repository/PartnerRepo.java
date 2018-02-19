package com.petertailor.belsokonyveles.repository;

import com.petertailor.belsokonyveles.domain.Partner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartnerRepo extends CrudRepository<Partner,Long>{

    Partner findByName(String s);

}
