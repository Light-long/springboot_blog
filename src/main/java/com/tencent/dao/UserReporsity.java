package com.tencent.dao;

import com.tencent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserReporsity extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username,String password);
}
