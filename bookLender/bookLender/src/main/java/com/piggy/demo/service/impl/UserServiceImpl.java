package com.piggy.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.piggy.demo.Dao.BookDao;
import com.piggy.demo.Dao.UserDao;
import com.piggy.demo.Dao.UbrDao;
import com.piggy.demo.domain.Book;
import com.piggy.demo.domain.User;
import com.piggy.demo.domain.UserBookRelation;
import com.piggy.demo.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Resource
    UbrDao ubrDao;

    @Resource
    BookDao bookDao;

    @Override
    public User getOne(Wrapper<User> queryWrapper) {
        System.out.println("11");
        User user = userDao.selectOne(queryWrapper);
        System.out.println("22");
        return user;
    }

    @Override
    public boolean save(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return userDao.deleteById(id) > 0;
    }

    @Override
    public User getById(Integer id) {
        return userDao.selectById(id);
    }

    @Override
    public boolean update(User user) {
        return userDao.updateById(user) > 0;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public LambdaQueryWrapper<User> searchUsers(User user){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.like(Strings.isNotEmpty(user.getUsername()),User::getUsername,user.getUsername());
        lqw.like(Strings.isNotEmpty(user.getName()),User::getName,user.getName());
        return lqw;
    }

    @Override
    public IPage<User> getPage(Integer currentPage, Integer pageSize, User user) {
        IPage page = new Page(currentPage,pageSize);
        userDao.selectPage(page,searchUsers(user));
        return page;
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
    public IPage<Book> getPagesByUserId(Integer userId, Integer currentPage, Integer pageSize, Book book , String filter) {
        IPage page = new Page(currentPage,pageSize);
        bookDao.selectPage(page,searchBooks(book));
        List<Book> borrowedBooks = ubrDao.selectBooksByUserId(userId);
        for (Book bookInB:borrowedBooks){
            bookInB.setBorrowed(true);
        }
        List<Book> allBooks = page.getRecords();

        //将所有 book 设置借阅标记
        for (Book bookInA:allBooks){
            for (Book bookInB:borrowedBooks){
                if (bookInA.getId() == bookInB.getId()){
                    bookInA.setBorrowed(true);
                }
            }
        }

        switch (filter){
            case "all":page.setRecords(allBooks);break;
            case "borrowed":page.setRecords(borrowedBooks);break;
            case "unborrowed":
                for (Book book1:allBooks){
                    if (!book1.isBorrowed()){
                        allBooks.remove(book1);
                    }
                }break;
        }
        return page;
    }

    @Override
    public boolean deleteBookById(Integer userId,Integer bookId) {
        LambdaQueryWrapper<UserBookRelation> lqw = new LambdaQueryWrapper<>();
        lqw.eq(userId != null,UserBookRelation::getUserId,userId);
        lqw.eq(bookId != null,UserBookRelation::getBookId,bookId);
        return ubrDao.delete(lqw) > 0;
    }

    @Override
    public boolean saveBookById(Integer userId, Integer bookId) {
        UserBookRelation userBookRelation = new UserBookRelation();
        userBookRelation.setUserId(userId);
        userBookRelation.setBookId(bookId);
        return ubrDao.insert(userBookRelation) > 0;
    }
}
