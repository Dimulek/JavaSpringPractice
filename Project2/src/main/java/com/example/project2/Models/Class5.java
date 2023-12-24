package com.example.project2.Models;

import java.util.List;
import java.util.Map;

public class Class5 extends AbsClass<Class5> {
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String line5;

    public Class5(String line1, String line2, String line3, String line4, String line5) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.line5 = line5;
    }

    @Override
    public void UpdateData(Class5 new_element) {
        this.line1 = new_element.GetLine1();
        this.line2 = new_element.GetLine2();
        this.line3 = new_element.GetLine3();
        this.line4 = new_element.GetLine4();
        this.line5 = new_element.GetLine5();
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
    public String GetLine4() {
        return line4;
    }
    public String GetLine5() {
        return line5;
    }

    @Override
    public Map<String, String> GetKeyValue(){
        return Map.of(
                "line1", GetLine1(),
                "line2", GetLine2(),
                "line3", GetLine3(),
                "line4", GetLine4(),
                "line5", GetLine5()
        );
    }


    public static List<String> GetKey(){
        return List.of(
                "line1",
                "line2",
                "line3",
                "line4",
                "line5"
        );
    }
}
