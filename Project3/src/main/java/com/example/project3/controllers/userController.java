package com.example.project3.controllers;

import com.example.project3.Models.modelUser;
import com.example.project3.Models.roleEnum;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/User")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class userController {
    @Autowired
    private com.example.project3.repo.userRepository userRepository;

    @GetMapping()
    public String User(Model model) {
        Iterable<modelUser> users = userRepository.findAll();
        model.addAttribute("Users", users);
        return "User";
    }

    Map<String, roleEnum> String_to_role = Map.of(
            "Администратор", roleEnum.ADMIN,
            "Пользватель", roleEnum.USER,
            "Каталогизатор", roleEnum.CATALOG,
            "Библиотекарь",  roleEnum.LIB
    );
    Map<roleEnum, String> Role_to_string = Map.of(
            roleEnum.ADMIN, "Администратор",
            roleEnum.USER, "Пользватель",
            roleEnum.CATALOG, "Каталогизатор",
            roleEnum.LIB ,"Библиотекарь"
    );

    List<String> String_of_role = List.of(
            "Администратор",
            "Пользватель",
            "Каталогизатор",
            "Библиотекарь"
    );

    @GetMapping("/Update/{id}")
    public String BookGoUpdate(Model model, @PathVariable("id") long id){
        var buff = userRepository.findById(id);
        model.addAttribute("user", buff);
        model.addAttribute("roles", String_of_role);
        model.addAttribute("rev_roles", Role_to_string);
        return "UserUpdate";
    }

    @PostMapping("/Update/{id}/AddRole")
    public String BookAddGenre(@PathVariable("id") long id_user,
                               @RequestParam("role_name") String role){
        modelUser user = userRepository.findById(id_user);
        user.getRoles().add(String_to_role.get(role));
        userRepository.save(user);
        return "redirect:/User/Update/" + id_user;
    }

    @PostMapping("/Update/{id}/DeleteRole/{roleName}")
    public String BookDeleteGenre(@PathVariable("id") long id_user,
                                  @PathVariable("roleName") String role){
        modelUser user = userRepository.findById(id_user);
        user.getRoles().removeIf(p -> p.getAuthority().equals(role));
        userRepository.save(user);
        return "redirect:/User/Update/" + id_user;
    }
}