package com.tencent.service;

import com.tencent.entity.Type;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    Type update(Long id,Type type) throws NotFoundException;

    void deleteType(Long id);

    Type getTypeByName(String name);
}
