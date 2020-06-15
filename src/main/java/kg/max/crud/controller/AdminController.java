package kg.max.crud.controller;

import kg.max.crud.model.Role;
import kg.max.crud.model.User;
import kg.max.crud.model.UserDTO;
import kg.max.crud.service.RoleService;
import kg.max.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping(value = "/")
    public ModelAndView allUsers() {
        List<User> userList = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", userList);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage(@ModelAttribute("userDTO") UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");
        User user = new User(userDTO);
        Set<Role> roles = new HashSet<>();

        for (String role : userDTO.getRoles()){
            if (role.equals("ROLE_USER")){
                roles.add(roleService.getRoleById(1));
            }
            if (role.equals("ROLE_ADMIN")){
                roles.add(roleService.getRoleById(2));
            }
        }
        if (user.getPassword() != null || !user.getPassword().isEmpty()){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        user.setRoles(roles);
        userService.add(user);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");

        User user = userService.getById(id);


        if (user != null){
            UserDTO userDTO = new UserDTO(user);

            for (Role role : user.getRoles()){
                if (role.getName().equals("ROLE_ADMIN")){
                    userDTO.setRoles(new ArrayList<>(Arrays.asList("ROLE_ADMIN")));
                }
                if (role.getName().equals("ROLE_USER")){
                    userDTO.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER")));
                }
            }
            modelAndView.addObject("userDTO", userDTO);
        }

        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        User user = new User(userDTO);
        if (user.getPassword().isEmpty() || user.getPassword() == null){
            user.setPassword(userService.getUserPasswordById(user.getId()));
        }else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        Set<Role> roles = new HashSet<>();

        for (String role : userDTO.getRoles()){
            if (role.equals("ROLE_USER")){
                roles.add(roleService.getRoleById(1));
            }
            if (role.equals("ROLE_ADMIN")){
                roles.add(roleService.getRoleById(2));
            }
        }
        user.setRoles(roles);

        userService.edit(user);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }

    @ModelAttribute("roles")
    public List<String> initRoles() {
        return roleService.getAllRolesName();
    }
}
