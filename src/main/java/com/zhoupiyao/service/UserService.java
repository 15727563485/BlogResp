package com.zhoupiyao.service;

import com.zhoupiyao.po.User;

public interface UserService {
    User checkLogin(String username, String password);

    User getUserById(Long id);
}
