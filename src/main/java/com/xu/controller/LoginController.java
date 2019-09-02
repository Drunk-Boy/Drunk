package com.xu.controller;

import com.xu.entities.User;
import com.xu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    User users;

    @Autowired
    UserService userService;

    @PostMapping("/login1")
    public ModelAndView getAllElder(@ModelAttribute(value = "user") User user,HttpServletRequest request){
        List<User> list =  userService.getAllUser();
        for(User user1:list){
            if(user1.getUserName().equals(user.getUserName())
                    &&user1.getPassWord().equals(user.getPassWord())){
                users.setUserName(user.getUserName());
                return new ModelAndView("main");
            }
        }
        return new ModelAndView("error/login");
    };

    @PostMapping("/register")
    public ModelAndView insert(@ModelAttribute(value = "user") User user, Model model, BindingResult bindingResult){
        for(User getUser : userService.getAllUser()){
            if(user.getUserName().equals(getUser.getUserName())){
                return new ModelAndView("register/registerView");
            }
        }
        userService.userInsert(user);
        //重定向过去，没有user参数，所以报错
        return new ModelAndView("login");
    }

    @PostMapping("/user/register")
    public String userRegister(HttpServletRequest request, Model model){
        String velue = request.getParameter("velue");
        if(velue=="" || velue==null){
            return "new";
        }
        for(User user : userService.getAllUser()){
            if(user.getUserName().equals(velue)){
                return "fail";
            }
        }
        return "success";
    }
}
