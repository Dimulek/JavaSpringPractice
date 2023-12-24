package com.example.project1.controller;

import com.example.project1.Class1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class mainController {
    List<Class1> list = new ArrayList<Class1>();
    @GetMapping("/")
    public String index(Model model){
        list.clear();
        list.add(new Class1("1"));
        list.add(new Class1("2"));
        list.add(new Class1("3"));
        list.add(new Class1("4"));
        model.addAttribute("Data", list);
        return "index";
    }
}
