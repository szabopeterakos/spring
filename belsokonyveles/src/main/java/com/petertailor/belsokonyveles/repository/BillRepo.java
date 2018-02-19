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
    List<Bill> findAllByPaymentTypeTypeNameAndPartnerNameAndReleaseDateBetween(String type, String partner, Date start, Date end);

    // for addbook indexpage
    List<Bill> findTop3ByOrderByIdDesc();

    // i o o
    List<Bill> findAllByPaymentTypeTypeName(String s);

    // o i o
    List<Bill> findAllByPartnerName(String s);

    // o o i
    List<Bill> findAllByReleaseDateBetween(Date start,Date end);

    // i i o
    List<Bill> findAllByPaymentTypeTypeNameAndPartnerName(String type, String partner);

    // o i i
    List<Bill> findAllByPartnerNameAndReleaseDateBetween(String partner,Date start,Date end);

    // i o i
    List<Bill> findAllByPaymentTypeTypeNameAndReleaseDateBetween(String s ,Date start,Date end);

    //for test
    List<Bill> findFirstByPartnerName(String s);
    List<Bill> findAllByAmount(Long s);
    List<Bill> findFirst3ById(Long i);




}
