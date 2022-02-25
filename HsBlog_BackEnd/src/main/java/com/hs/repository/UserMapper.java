package com.hs.repository;

import com.hs.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @Entity com.hs.model.User
*/
@Mapper
public interface UserMapper  {
    User findByUsername(String username);
}
