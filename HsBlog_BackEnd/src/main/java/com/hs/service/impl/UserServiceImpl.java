package com.hs.service.impl;

import com.hs.constants.exception.PasswordIncorrectException;
import com.hs.constants.exception.UserNotExistException;
import com.hs.entity.User;
import com.hs.repository.UserMapper;
import com.hs.service.UserService;
import com.hs.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        //List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        //        // commaSeparatedStringToAuthorityList的意思是逗号分隔的String转换为权限list
        //        .commaSeparatedStringToAuthorityList(user.getRole());
        if (user==null){
            throw new UsernameNotFoundException("用户不存在!");
        }
        return user;
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
