package com.tencent.service.impl;

import com.tencent.dao.UserReporsity;
import com.tencent.entity.User;
import com.tencent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReporsity userReporsity;

    @Override
    public User checkUer(String username, String password) {
        User user = userReporsity.findByUsernameAndPassword(username, password);
        return user;
    }
}
