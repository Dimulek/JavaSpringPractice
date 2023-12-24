package com.example.project2;

import com.example.project2.Models.AbsClass;

import java.util.ArrayList;
import java.util.List;

public class DAO<T extends AbsClass<T>> {
    private List<T> list = new ArrayList<>();
    private int unique_id = 0;


    public T GetByID(int id){
        return list.stream().filter(element -> element.GetID() == id).findAny().orElse(null);
    }

    public void AddElement(T element) {
        element.SetID(++unique_id);
        list.add(element);
    }

    public void UpdateElement(int id, T new_element){
        T updatePerson = GetByID(id);
        updatePerson.UpdateData(new_element);
    }

    public void DeleteElement(int id){
        list.removeIf(p-> p.GetID() == id);
    }

    public List<T> GetInstance() {
        return list;
    }
}
