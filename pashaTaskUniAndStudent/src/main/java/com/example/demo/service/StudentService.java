package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.Collection;

public interface StudentService {
    public abstract Collection<Student> getStudents();
    public abstract void addStudent(Student student);
    public abstract void updateStudent(int id, Student student);
    public abstract void deleteStudents(int id);
    public  Collection<Student> getStudentsByCollegeId(int collegeId);
}
