package com.example.blog.service.serviceImpl;

import com.example.blog.dao.TypeDao;
import com.example.blog.exception.NotFoundException;
import com.example.blog.pojo.Type;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional//放置在事务中
    @Override
    public int saveType(Type type) {//保存类型
        return typeDao.saveType(type);
    }

    @Transactional//放置在事务中
    @Override
    public Type getType(Long id) {
        return typeDao.getTypeById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Transactional//放置在事务中
    @Override
    public List<Type> listTypeAndBlog() {
        return typeDao.listTypeAndBlog();
    }

    @Transactional//放置在事务中
    @Override
    public int updateType(Type type) {
        Type type1 = typeDao.getTypeById(type.getId());
        if (type1 == null) {
            throw new NotFoundException("不存在该类型");
        }
        return typeDao.updateType(type);
    }

    @Transactional//放置在事务中
    @Override
    public int deleteType(Long id) {
        return typeDao.deleteType(id);
    }

}
