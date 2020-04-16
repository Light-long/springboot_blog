package com.tencent.service;

import com.tencent.entity.User;

public interface UserService {

    User checkUer(String username, String password);
}
