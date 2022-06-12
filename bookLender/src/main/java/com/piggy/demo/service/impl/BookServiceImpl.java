package com.piggy.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piggy.demo.Dao.BookDao;
import com.piggy.demo.Dao.UbrDao;
import com.piggy.demo.domain.Book;
import com.piggy.demo.domain.UserBookRelation;
import com.piggy.demo.service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    @Resource
    private UbrDao ubrDao;

    @Override
    public boolean save(Book book) {
        return bookDao.insert(book) > 0;
    }

    @Override
    public boolean update(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        LambdaQueryWrapper<UserBookRelation> lqw = new LambdaQueryWrapper();
        lqw.eq(UserBookRelation::getBookId,id);
        ubrDao.delete(lqw);
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDao.selectList(null);
    }

    @Override
    public LambdaQueryWrapper<Book> searchBooks(Book book){
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(Strings.isNotEmpty(book.getType()),Book::getType,book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()),Book::getName,book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()),Book::getDescription,book.getDescription());
        return lqw;
    }
    @Override
    public IPage<Book> getPage(Integer currentPage, Integer pageSize,Book book) {
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,searchBooks(book));
        return page;
    }

    //@Override
    //public boolean sortById() {
    //    LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    //
    //    bookDao.selectList(lambdaQueryWrapper);
    //    return false;
    //}
}
