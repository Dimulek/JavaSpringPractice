package com.example.project2.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.of;

public class Class1 extends AbsClass<Class1> {
    public String line1;

    public Class1(String line1) {
        this.line1 = line1;
    }

    @Override
    public void UpdateData(Class1 new_element) {
        this.line1 = new_element.GetLine1();
    }

    public String GetLine1() {
        return line1;
    }

    @Override
    public Map<String, String> GetKeyValue(){
       return Map.of(
               "line1", GetLine1()
       );
    }

    public static List<String> GetKey(){
        return List.of(
                "line1"
        );
    }
}
