package com.hs.hsblog_backend.service.impl;

import com.hs.hsblog_backend.constants.exception.PasswordIncorrectException;
import com.hs.hsblog_backend.constants.exception.UserNotExistException;
import com.hs.hsblog_backend.entity.User;
import com.hs.hsblog_backend.repository.UserMapper;
import com.hs.hsblog_backend.service.UserService;
import com.hs.hsblog_backend.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Hs
 * @Date 2021/11/30 13:51
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                // commaSeparatedStringToAuthorityList的意思是逗号分隔的String转换为权限list
                .commaSeparatedStringToAuthorityList(user.getRole());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),user.getPassword(),grantedAuthorities);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User checkUsernameAndPassword(String username,String password) {
        User user = userMapper.findByUsername(username);
        if (user==null){
            throw new UserNotExistException("用户名不存在");
        }else if (!EncodeUtil.matchPassword(password,user.getPassword())){
            throw new PasswordIncorrectException("密码错误");
        }
        return user;
    }
}
