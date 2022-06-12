package com.piggy.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.piggy.demo.domain.Book;
import com.piggy.demo.domain.User;
import com.piggy.demo.domain.UserBookRelation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getOne(Wrapper<User> queryWrapper);//依条件获取用户信息
    boolean save(User user);//新增用户信息
    boolean delete(Integer id);//删除用户信息
    User getById(Integer id);//以id获取用户
    boolean update(User user);//更新用户信息
    List<User> getAll();//获取所有用户
    LambdaQueryWrapper<User> searchUsers(User user);//搜索用户
    IPage<User> getPage(Integer currentPage, Integer pageSize, User user);//获取用户分页

    LambdaQueryWrapper<Book> searchBooks(Book book);//搜索借阅图书
    IPage<Book> getPagesByUserId(Integer userId,Integer currentPage, Integer pageSize, Book book , String filter);//获得借阅图书分页
    boolean deleteBookById(Integer userId,Integer bookId);//删除借阅信息
    boolean saveBookById(Integer userId,Integer bookId);//添加借阅信息
}
