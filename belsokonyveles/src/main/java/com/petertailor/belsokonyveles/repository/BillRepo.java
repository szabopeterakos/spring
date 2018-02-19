package com.petertailor.belsokonyveles.repository;

import com.petertailor.belsokonyveles.domain.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepo extends CrudRepository<Bill,Long>{

    @Override
    Iterable<Bill> findAll();

    List<Bill> findTop3ByOrderByIdDesc();

    List<Bill> findFirstByPartnerName(String s);
    List<Bill> findAllByAmount(Long s);
    List<Bill> findFirst3ById(Long i);




}
