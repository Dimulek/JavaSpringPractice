package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class calcController {

    public String calc(){
        String line = param1 + " " + act_convert_char.get(act) + " " + param2 + " = ";
        switch (act){
            case Plus -> line += (param1 + param2);
            case Minus -> line += (param1 - param2);
            case Dev -> {
                if (param2 == 0)
                    line = "You cant dev using 0";
                else
                    line += (param1 / param2);
                break;
            }
            case Mult -> line += (param1 * param2);
            case Mult2 -> line += Math.pow(param1, param2);
        }
        return line;
    }

    @GetMapping("/Calc")
    public String calc(Model model){
        model.addAttribute("Param1", param1);
        model.addAttribute("Param2", param2);
        model.addAttribute("Act", act);
        model.addAttribute("Operation", calc());
        return "calc";
    }

    @GetMapping("/Calc/operation")
    public String operation(Model model){
        model.addAttribute("ActTypes", act_convert_char);
        model.addAttribute("Param1", param1);
        model.addAttribute("Param2", param2);
        model.addAttribute("Act", act);
        return "operation";
    }

    @PostMapping("/Calc/Calculate")
    public String calculate(Model model, @RequestParam("Data1") double data1,
                            @RequestParam("Act") String Act,
                            @RequestParam("Data2") double data2){
        param1 = data1;
        param2 = data2;
        act = char_convert_act.get(Act);
        return "redirect:/Calc";
    }

    enum ActTypes{
        Plus,
        Minus,
        Dev,
        Mult,
        Mult2
    }

    Map<ActTypes, String> act_convert_char = Map.of(
            ActTypes.Plus, "+",
            ActTypes.Minus, "-",
            ActTypes.Dev, "/",
            ActTypes.Mult, "*",
            ActTypes.Mult2, "^");

    Map<String, ActTypes> char_convert_act = Map.of(
            "+", ActTypes.Plus,
            "-", ActTypes.Minus,
            "/", ActTypes.Dev,
            "*", ActTypes.Mult,
            "^", ActTypes.Mult2);

    double param1 = 0, param2 = 0;
    ActTypes act = ActTypes.Plus;
}
