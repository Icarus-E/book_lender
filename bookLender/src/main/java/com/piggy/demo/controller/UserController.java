package com.piggy.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.piggy.demo.controller.utils.Result;
import com.piggy.demo.domain.Book;
import com.piggy.demo.domain.User;
import com.piggy.demo.domain.UserBookRelation;
import com.piggy.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    UserService userService;

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println("111");
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        System.out.println("222");
        User user1 = userService.getOne(lambdaQueryWrapper);//依据用户名得到用户
        System.out.println("333");
        if (user1 == null){
            return Result.error("用户名不存在");
        }
        if (!user1.getPassword().equals(user.getPassword())){
            return Result.error("密码错误");
        }
        return Result.success(user,"登录成功");
    }

    //新建用户
    @PostMapping
    public Result save(@RequestBody User user){
        if (userService.save(user)){
            return Result.success(null,"添加成功^ V ^");
        }else{
            return Result.error("添加失败!");
        }
    }

    //修改用户
    @PutMapping
    public Result update(@RequestBody User user){
        if (userService.update(user)){
            return Result.success(null,"更新成功");
        }else{
            return Result.error("数据同步失败");
        }
    }

    //删除用户
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        if (userService.delete(id)){
            return Result.success(null,"删除成功!");
        }else{
            return Result.error("数据同步失败");
        }
    }

    //初始化原用户信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User resUser = userService.getById(id);
        if (resUser != null){
            return Result.success(resUser,"获取成功");
        }else {
            return Result.error("数据同步失败");
        }
    }

    //分页查询
    @GetMapping("/{currentPage}/{pageSize}")//这里这么写就行了
    public Result getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,String name,User user){
        System.out.println(name);
        System.out.println(user);
        IPage<User> page = userService.getPage(currentPage, pageSize,user);
        if(currentPage > page.getPages())
            page = userService.getPage((int) page.getPages(),pageSize,user);
        return Result.success(page,"获取成功");
    }

    //图书借阅
    @PostMapping("/books")
    public Result saveBook(@RequestBody UserBookRelation ubr){
        if (userService.saveBookById(ubr.getUserId(),ubr.getBookId())){
            return Result.success(null,"添加成功^ V ^");
        }else{
            return Result.error("添加失败!");
        }
    }

    //图书归还
    @DeleteMapping("/books/{userId}/{bookId}")
    public Result deleteBook(@PathVariable Integer userId,@PathVariable Integer bookId){
        if(userService.deleteBookById(userId,bookId)){
            return Result.success(null,"删除成功");
        }else{
            return Result.error("数据同步失败");
        }
    }

    //借阅图书分页查询
    @GetMapping("/books/{currentPage}/{pageSize}/{userId}")//这里这么写就行了
    public Result getPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize,@PathVariable Integer userId,Book book){
        IPage<Book> page = userService.getPagesByUserId(userId,currentPage,pageSize,book,"all");
        System.out.println(page.getRecords());
        if(currentPage > page.getPages())
            page = userService.getPagesByUserId(userId,(int) page.getPages(),pageSize,book,"all");
        return Result.success(page,"获取成功");
    }
}
