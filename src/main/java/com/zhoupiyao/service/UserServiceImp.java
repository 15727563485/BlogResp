package com.zhoupiyao.service;

import com.zhoupiyao.dao.UserRepository;
import com.zhoupiyao.po.User;
import com.zhoupiyao.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkLogin(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }
}
