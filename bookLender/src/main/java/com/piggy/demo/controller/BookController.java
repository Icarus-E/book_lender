package com.piggy.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.piggy.demo.controller.utils.Result;
import com.piggy.demo.domain.Book;
import com.piggy.demo.service.BookService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping("/books")
public class BookController {
    @Resource
    private BookService bookService;

    //新增图书
    @PostMapping
    public Result save(@RequestBody Book book){
        if (bookService.save(book)){
            return Result.success(null,"添加成功^ V ^");
        }else{
            return Result.error("添加失败!");
        }
    }

    //修改图书
    @PutMapping
    public Result update(@RequestBody Book book){
        if (bookService.update(book)){
            return Result.success(null,"更新成功");
        }else{
            return Result.error("数据同步失败");
        }
    }

    //删除图书
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (bookService.delete(id)){
            return Result.success(null,"删除成功!");
        }else{
            return Result.error("数据同步失败");
        }
    }

    //初始化原图书数据
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Book resBook = bookService.getById(id);
        if (resBook != null){
            return Result.success(resBook,"获取成功");
        }else {
            return Result.error("数据同步失败");
        }
    }

    //分页查询
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,Book book){
        System.out.println(book);
        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        if(currentPage > page.getPages())
            page = bookService.getPage((int) page.getPages(),pageSize,book);
        return Result.success(page,"获取成功");
    }
}
