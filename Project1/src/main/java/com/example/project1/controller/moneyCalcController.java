package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/")
public class moneyCalcController {

    enum MoneyTypes{
        Dollar,
        Ruble,
        Euro,
        Irl_real,
        Bitcoin
    }

    Map<MoneyTypes, String> money_convert_name = Map.of(
            MoneyTypes.Dollar, "Доллары",
            MoneyTypes.Ruble, "Рубли",
            MoneyTypes.Euro, "Евро",
            MoneyTypes.Irl_real, "Ирлански риал",
            MoneyTypes.Bitcoin, "Биткоин");
    Map<String, MoneyTypes> name_convert_money = Map.of(
            "Доллары", MoneyTypes.Dollar,
            "Рубли", MoneyTypes.Ruble,
            "Евро", MoneyTypes.Euro,
            "Ирлански риал", MoneyTypes.Irl_real,
            "Биткоин", MoneyTypes.Bitcoin);
    Map<MoneyTypes, Double> money_convert_cost = Map.of(
            MoneyTypes.Dollar, 90.96,
            MoneyTypes.Ruble, 1.0,
            MoneyTypes.Euro, 98.09,
            MoneyTypes.Irl_real, 0.0023,
            MoneyTypes.Bitcoin, 3713523.86);

    MoneyTypes first_selector = MoneyTypes.Dollar;
    MoneyTypes second_selector = MoneyTypes.Ruble;
    double value = 1;
    int i = 0;

    public String calc(){
        double x = (value * money_convert_cost.get(first_selector))/money_convert_cost.get(second_selector);
        return String.format("%.10f", x);
    }

    @GetMapping("/Money")
    public String MoneyCalc(Model model){
        model.addAttribute("Num", i);
        model.addAttribute("MoneyName", money_convert_name);
        model.addAttribute("MoneyCost", money_convert_name);
        model.addAttribute("FirstSelect", first_selector);
        model.addAttribute("SecondSelect", second_selector);
        model.addAttribute("Value", value);
        model.addAttribute("Result", calc());
        return "moneyCalc";
    }

    @PostMapping("/Money/Calculate")
    public String calculate(Model model, @RequestParam("Data") double data,
                            @RequestParam("From") String From,
                            @RequestParam("To") String To){
        value = data;
        first_selector = name_convert_money.get(From);
        second_selector = name_convert_money.get(To);
        return "redirect:/Money";
    }

    @GetMapping("/Money/Add")
    public String add(Model model){
        ++i;
        return "redirect:/Money";
    }
}
