package com.hs.hsblog_backend.repository;

import com.hs.hsblog_backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @Entity com.hs.hsblog_backend.model.User
*/
@Mapper
public interface UserMapper  {
    User findByUsername(String username);
}
