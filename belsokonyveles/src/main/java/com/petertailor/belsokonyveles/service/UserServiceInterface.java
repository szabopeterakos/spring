package com.petertailor.belsokonyveles.service;

import com.petertailor.belsokonyveles.domain.User;

public interface UserServiceInterface {

    User findByEmail(String mail);

}
