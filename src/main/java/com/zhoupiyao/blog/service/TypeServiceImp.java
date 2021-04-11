package com.zhoupiyao.blog.service;

import com.zhoupiyao.blog.dao.TypeRepository;
import com.zhoupiyao.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeServiceImp implements TypeService{
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.delete(id);
    }

    @Override
    public Type upadteType(Long id, Type type) {
        Type t=typeRepository.findOne(id);
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Override
    public Type getTypeById(Long id) {
        return typeRepository.findOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<Type> listAllType() {
        return typeRepository.findAll();
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=new PageRequest(0,size,sort);
        return typeRepository.findtypeTop(pageable);
    }
}
