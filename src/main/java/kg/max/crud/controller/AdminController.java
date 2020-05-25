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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User user = new User();
        Set<Role> roles = new HashSet<>();

        if (userDTO.getRole().equals("ROLE_ADMIN")) {
            roles.add(new Role(1, "ROLE_USER"));
            roles.add(new Role(2, "ROLE_ADMIN"));
        } else {
            roles.add(new Role(1, "ROLE_USER"));
        }

        user.setFirstname(userDTO.getFirstname());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setRoles(roles);
        userService.add(user);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");

        User user = userService.getById(id);
        UserDTO userDTO = new UserDTO();

        if (user != null){
            for (Role role : user.getRoles()){
                if (role.getName().equals("ROLE_ADMIN")){
                    userDTO.setRole("ROLE_ADMIN");
                    break;
                }
                userDTO.setRole("ROLE_USER");
            }
            userDTO.setId(user.getId());
            userDTO.setFirstname(user.getFirstname());
            userDTO.setSurname(user.getSurname());
            userDTO.setUsername(user.getUsername());
            modelAndView.addObject("userDTO", userDTO);
        }

        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/");

        User user = new User();
        Set<Role> roles = new HashSet<>();

        if (userDTO.getRole().equals("ROLE_ADMIN")) {
            roles.add(new Role(1, "ROLE_USER"));
            roles.add(new Role(2, "ROLE_ADMIN"));
        } else {
            roles.add(new Role(1, "ROLE_USER"));
        }
        user.setId(userDTO.getId());
        user.setFirstname(userDTO.getFirstname());
        user.setSurname(userDTO.getSurname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
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
