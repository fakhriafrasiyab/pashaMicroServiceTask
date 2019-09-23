package com.example.demo.service;

import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
@Service
public class StudentServiceImpl implements StudentService{
    private static HashMap<Integer,Student> studentMap = new HashMap<>();
    int lastId=3;
    static {
        Student st1 = new Student();
        st1.setId(1);
        st1.setUserName("Elvin Mahmudov");
        st1.setCollege_id(1);
        studentMap.put(st1.getId(), st1);

        Student st2 = new Student();
        st2.setId(2);
        st2.setUserName("Ayxan Aghazade");
        st2.setCollege_id(2);
        studentMap.put(st2.getId(), st2);
    }

    @Override
    public Collection<Student> getStudents() {
        return studentMap.values();
    }

    @Override
    public void addStudent(Student student) {
        student.setId(lastId);
        studentMap.put(lastId,student);
        lastId++;
    }

    @Override
    public void updateStudent(int id, Student student) {
        studentMap.remove(id);
        student.setId(id);
        studentMap.put(id,student);
    }

    @Override
    public void deleteStudents(int id) {
        studentMap.remove(id);
    }

    public  Collection<Student> getStudentsByCollegeId(int collegeId) {
        ArrayList<Student> students = new ArrayList<Student>();
        for (Student student : studentMap.values()) {
            if (student.getCollege_id() == collegeId){
                students.add(student);
            }
        }
        return students;
    }
}
