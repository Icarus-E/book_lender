package com.piggy.demo.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.piggy.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao extends BaseMapper<User> {
}
