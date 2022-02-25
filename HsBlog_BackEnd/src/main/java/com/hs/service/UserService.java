package com.hs.service;


import com.hs.entity.User;

/**
 * @author Hs
 * @Date 2021/11/30 13:51
 */
public interface UserService {
    User findByUsername(String username);

    User checkUsernameAndPassword(String username,String password);
}
