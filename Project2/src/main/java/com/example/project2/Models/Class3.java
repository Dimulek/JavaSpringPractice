package com.example.project2.Models;

import java.util.List;
import java.util.Map;

public class Class3 extends AbsClass<Class3> {
    private String line1;
    private String line2;
    private String line3;

    public Class3(String line1, String line2, String line3) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }

    @Override
    public void UpdateData(Class3 new_element) {
        this.line1 = new_element.GetLine1();
        this.line2 = new_element.GetLine2();
        this.line3 = new_element.GetLine3();
    }

    public String GetLine1() {
        return line1;
    }
    public String GetLine2() {
        return line2;
    }
    public String GetLine3() {
        return line3;
    }

    @Override
    public Map<String, String> GetKeyValue(){
        return Map.of(
                "line1", GetLine1(),
                "line2", GetLine2(),
                "line3", GetLine3()
        );
    }


    public static List<String> GetKey(){
        return List.of(
                "line1",
                "line2",
                "line3"
        );
    }
}
