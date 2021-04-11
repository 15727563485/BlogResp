package com.zhoupiyao.blog.service;

import com.zhoupiyao.blog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    void deleteType(Long id);
    Type upadteType(Long id,Type type);
    Type getTypeById(Long id);
    Type getTypeByName(String name);
    List<Type> listAllType();
    Page<Type> listType(Pageable pageable);
    List<Type> listTypeTop(Integer size);

}
