package com.petertailor.belsokonyveles.repository;

import com.petertailor.belsokonyveles.domain.PaymentType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentTypeRepo extends CrudRepository<PaymentType,Long> {

    PaymentType findByTypeName(String s);

    @Override
    List<PaymentType> findAll();
}
