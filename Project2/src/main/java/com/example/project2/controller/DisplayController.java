package com.example.project2.controller;

import com.example.project2.DAO;
import com.example.project2.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/Display")
public class DisplayController {
    private static DAO<Class1> dao_1 = new DAO<>();
    private static DAO<Class2> dao_2 = new DAO<>();
    private static DAO<Class3> dao_3 = new DAO<>();
    private static DAO<Class4> dao_4 = new DAO<>();
    private static DAO<Class5> dao_5 = new DAO<>();

    private static String display_name = "";

    private static Map<String, DAO> using_dao = Map.of(
            "Class1", dao_1,
            "Class2", dao_2,
            "Class3", dao_3,
            "Class4", dao_4,
            "Class5", dao_5
    );

    private static Map<String, Integer> using_integer = Map.of(
            "Class1", 1,
            "Class2", 2,
            "Class3", 3,
            "Class4", 4,
            "Class5", 5
    );

    private static Map<Integer, Class> using_Class = Map.of(
            1, Class1.class,
            2, Class2.class,
            3, Class3.class,
            4, Class4.class,
            5, Class5.class
    );

    public static boolean IsDaoExist() {
        return using_dao.containsKey(display_name);
    }

    public static DAO GetDao() {
        return using_dao.get(display_name);
    }

    public static int GetIndexClass() {
        return using_integer.get(display_name);
    }

    public static Class GetClassByIndex(int index) {
        return using_Class.get(index);
    }

    public static List<String> GetClassOption() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class type = GetClassByIndex(GetIndexClass());
        Method method = type.getMethod("GetKey", null);
        return (List<String>) method.invoke(null);
    }

    @GetMapping()
    public String Display(Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(!IsDaoExist())
            return "redirect:/";

        model.addAttribute("ClassIndex", GetIndexClass());

        model.addAttribute("Data", GetDao().GetInstance());

        model.addAttribute("Keys", GetClassOption());
        return "ClassShow";
    }

    @PostMapping()
    public String DisplayClass(Model model, @RequestParam("display_name") String name){
        display_name = name;
        return "redirect:/Display";
    }

    @PostMapping("/Post1")
    public String PostClass1(Model model, @ModelAttribute("model") Class1 class_model){
        GetDao().AddElement(class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Post2")
    public String PostClass2(Model model, @ModelAttribute("model") Class2 class_model){
        GetDao().AddElement(class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Post3")
    public String PostClass3(Model model, @ModelAttribute("model") Class3 class_model){
        GetDao().AddElement(class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Post4")
    public String PostClass4(Model model, @ModelAttribute("model") Class4 class_model){
        GetDao().AddElement(class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Post5")
    public String PostClass5(Model model, @ModelAttribute("model") Class5 class_model){
        GetDao().AddElement(class_model);
        return "redirect:/Display";
    }

    @PostMapping("/Delete/{id}")
    public String DeleteClass(Model model, @PathVariable("id") int id){

        if(!IsDaoExist())
            return "redirect:/";
        var dao = GetDao();
        dao.DeleteElement(id);
        return "redirect:/Display";
    }
}
