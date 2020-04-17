package com.tencent.dao;

import com.tencent.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeReposity extends JpaRepository<Type,Long> {

    Type findByName(String name);
}
