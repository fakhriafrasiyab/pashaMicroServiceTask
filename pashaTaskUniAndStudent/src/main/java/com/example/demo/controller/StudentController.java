package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Collection;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student")
    public ResponseEntity<Object> getStudent() {
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
    }
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editStudent(@PathVariable ("id") int id, @RequestBody Student student) {
        studentService.updateStudent(id,student);
        return new ResponseEntity<>("Student is updated successfully", HttpStatus.OK);
    }
    @RequestMapping(value = "/student{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeStudent(@PathVariable("id") int id){
        studentService.deleteStudents(id);
        return new ResponseEntity<>("Student is removed from list", HttpStatus.OK);
    }
    @RequestMapping(value = "/studentSearch")
    public ResponseEntity<Object> getStudentSearch(@RequestParam String location) throws IOException {
        int id=0;
        URL urlForGetRequest = new URL("http://localhost:8082/getByCity?location="+location);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            try {
                JSONObject jsonObject = new JSONObject(response.toString());
                id = (int) jsonObject.get("id");
                System.out.println(id);
            }catch (JSONException err){
            }

            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
        Collection<Student> studentResponse = studentService.getStudentsByCollegeId(id);
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }
}
