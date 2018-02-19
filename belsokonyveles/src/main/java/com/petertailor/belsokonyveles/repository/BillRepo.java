package com.petertailor.belsokonyveles.repository;

import com.petertailor.belsokonyveles.domain.Bill;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepo extends CrudRepository<Bill,Long>{

    @Override
    // o o o
    Iterable<Bill> findAll();

    // i i i

    // for addbook indexpage
    List<Bill> findTop3ByOrderByIdDesc();

    // i o o
    List<Bill> findAllByPaymentTypeTypeName(String s);

    // o i o
    List<Bill> findAllByPartnerName(String s);

    // o o i
    List<Bill> findAllByReleaseDateBetween(Date start,Date end);

    // i i o

    // o i i

    // i o i

    //for test
    List<Bill> findFirstByPartnerName(String s);
    List<Bill> findAllByAmount(Long s);
    List<Bill> findFirst3ById(Long i);




}
