package com.example.project2.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsClass<T>{
    private int id = -1;
    public AbsClass(){}
    public void SetID(int id){
        this.id = id;
    }

    public int GetID(){
        return id;
    }

    public abstract void UpdateData(T new_element);
    public abstract Map<String, String> GetKeyValue();
}
