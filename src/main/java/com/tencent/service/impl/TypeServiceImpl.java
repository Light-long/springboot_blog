package com.tencent.service.impl;

import com.tencent.dao.TypeReposity;
import com.tencent.entity.Type;
import com.tencent.service.TypeService;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeReposity typeReposity;

    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeReposity.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeReposity.getOne(id);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeReposity.findAll(pageable);
    }

    @Override
    @Transactional
    public Type update(Long id, Type type) throws NotFoundException {
        Type t = typeReposity.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该分类");
        }
        BeanUtils.copyProperties(type,t);

        return typeReposity.save(t);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeReposity.deleteById(id);
    }

    @Override
    @Transactional
    public Type getTypeByName(String name) {
        return typeReposity.findByName(name);
    }

}
