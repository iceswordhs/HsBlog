package com.hs.hsblog_backend.service;


import com.hs.hsblog_backend.entity.User;

/**
 * @author Hs
 * @Date 2021/11/30 13:51
 */
public interface UserService {
    User findByUsername(String username);

    User checkUsernameAndPassword(String username,String password);
}
