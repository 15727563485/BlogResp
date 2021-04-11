package com.zhoupiyao.blog.service;

import com.zhoupiyao.blog.po.User;

public interface UserService {
    User checkLogin(String username,String password);
    User getUserById(Long id);
}
