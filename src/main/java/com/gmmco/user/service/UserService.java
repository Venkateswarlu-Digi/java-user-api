package com.gmmco.user.service;

import com.gmmco.user.model.User;

public interface UserService {

    User getUser(Integer id, String email);

}
