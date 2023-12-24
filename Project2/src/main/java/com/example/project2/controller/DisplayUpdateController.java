package com.example.project2.controller;

import com.example.project2.DAO;
import com.example.project2.Models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.in;

@Controller
@RequestMapping("/Display/Update")
public class DisplayUpdateController {

    int now_id = 0;

    @PostMapping("/{id}")
    public String Update(Model model, @PathVariable("id") int id) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, String> entety_data = new HashMap<>();

        if(!DisplayController.IsDaoExist())
            return "redirect:/";
        now_id = id;
        var object = DisplayController.GetDao().GetByID(id);

        model.addAttribute("ClassIndex", DisplayController.GetIndexClass());

        model.addAttribute("Data", object.GetKeyValue());

        model.addAttribute("Keys", DisplayController.GetClassOption());
        return "ClassUpdate";
    }

    @PostMapping("/Put1")
    public String PutClass1(@ModelAttribute("model") Class1 class_model){
        DisplayController.GetDao().UpdateElement(now_id, class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Put2")
    public String PutClass2(@ModelAttribute("model") Class2 class_model){
        DisplayController.GetDao().UpdateElement(now_id, class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Put3")
    public String PutClass3(@ModelAttribute("model") Class3 class_model){
        DisplayController.GetDao().UpdateElement(now_id, class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Put4")
    public String PutClass4(@ModelAttribute("model") Class4 class_model){
        DisplayController.GetDao().UpdateElement(now_id, class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Put5")
    public String PutClass5(Model model, @ModelAttribute("model") Class5 class_model){
        DisplayController.GetDao().UpdateElement(now_id, class_model);
        return "redirect:/Display";
    }
}
