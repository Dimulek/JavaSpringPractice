package com.example.project2.Models;

import java.util.List;
import java.util.Map;

public class Class2 extends AbsClass<Class2> {
    private String line1;
    private String line2;

    public Class2(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    @Override
    public void UpdateData(Class2 new_element) {
        this.line1 = new_element.GetLine1();
        this.line2 = new_element.GetLine2();
    }

    public String GetLine1() {
        return line1;
    }
    public String GetLine2() {
        return line2;
    }

    @Override
    public Map<String, String> GetKeyValue(){
        return Map.of(
                "line1", GetLine1(),
                "line2", GetLine2()
        );
    }

    public static List<String> GetKey(){
        return List.of(
                "line1",
                "line2"
        );
    }
}
