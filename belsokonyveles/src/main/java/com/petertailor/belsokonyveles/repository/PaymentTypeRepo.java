package com.petertailor.belsokonyveles.repository;

import com.petertailor.belsokonyveles.domain.PaymentType;
import org.springframework.data.repository.CrudRepository;

public interface PaymentTypeRepo extends CrudRepository<PaymentType,Long> {

    PaymentType findByTypeName(String s);

}
