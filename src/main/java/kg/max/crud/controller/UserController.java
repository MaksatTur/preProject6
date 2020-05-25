package kg.max.crud.controller;

import kg.max.crud.model.User;
import kg.max.crud.model.UserDTO;
import kg.max.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public ModelAndView aboutMe() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getById(principal.getId());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(value = "/")
//    public ModelAndView allUsers() {
//        List<User> userList = userService.allUsers();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("users", userList);
//        modelAndView.setViewName("allUsers");
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/add")
//    public ModelAndView addPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("addUserForm");
//        return modelAndView;
//    }
//
//    @PostMapping(value = "/add")
//    public ModelAndView addUser(@ModelAttribute("user") User user){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        userService.add(user);
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public ModelAndView editPage(@PathVariable("id") long id) {
//        User user = userService.getById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("editUserForm");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//
//    @PostMapping(value = "/edit")
//    public ModelAndView editUser(@ModelAttribute("user") User user) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        userService.edit(user);
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/delete/{id}")
//    public ModelAndView deleteUser(@PathVariable("id") long id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        User user = userService.getById(id);
//        userService.delete(user);
//        return modelAndView;
//    }
}
