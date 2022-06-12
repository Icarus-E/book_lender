package com.piggy.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.piggy.demo.domain.Book;

import java.util.List;

public interface BookService {
    boolean save(Book book);//保存图书信息
    boolean update(Book book);//更新图书信息
    boolean delete(Integer id);//删除图书信息
    Book getById(Integer id);//以id获取图书
    List<Book> getAll();//获取所有图书信息
    LambdaQueryWrapper<Book> searchBooks(Book book);//搜索目标图书
    IPage<Book> getPage(Integer currentPage,Integer pageSize,Book book);//获取图书分页
}
