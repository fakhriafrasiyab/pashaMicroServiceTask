package com.example.demo.services;

import com.example.demo.model.College;

import java.util.Collection;

public interface CollegeService {
    public abstract Collection<College> getCollege();
    public abstract void addCollege(College college);
    public abstract void updateCollege(int id, College college);
    public abstract void deleteCollege(int id);
    public College getByName(String location);
}
