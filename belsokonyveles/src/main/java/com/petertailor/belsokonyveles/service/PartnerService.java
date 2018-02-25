package com.petertailor.belsokonyveles.service;

import com.petertailor.belsokonyveles.domain.Partner;
import com.petertailor.belsokonyveles.repository.PartnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService {

    private PartnerRepo partnerRepo;

    @Autowired
    public void setPartnerRepo(PartnerRepo partnerRepo) {
        this.partnerRepo = partnerRepo;
    }

    public Iterable<Partner> partnerList(){
        return this.partnerRepo.findAll();
    }
}
