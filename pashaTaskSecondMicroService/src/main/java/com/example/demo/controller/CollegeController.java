package com.example.demo.controller;

import com.example.demo.model.College;
import com.example.demo.services.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

@RestController
public class CollegeController {

    @Autowired
    CollegeService collegeService;

    @RequestMapping(value = "/college")
    public ResponseEntity<Object> showCollege(){
        return new ResponseEntity<>(collegeService.getCollege(), HttpStatus.OK);
    }

    @RequestMapping(value = "/college", method = RequestMethod.POST)
    public ResponseEntity<Object> createCollege(@RequestBody College college) {
        collegeService.addCollege(college);
        return new ResponseEntity<>("New College is added to list",HttpStatus.CREATED);
    }

    @RequestMapping(value = "/college/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> editCollege(@PathVariable("id") int id, @RequestBody College college) {
        collegeService.updateCollege(id,college);
        return new ResponseEntity<>("College is updated",HttpStatus.OK);
    }

    @RequestMapping(value = "/college/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeCollege(@PathVariable("id") int id){
        collegeService.deleteCollege(id);
        return new ResponseEntity<>("College is deleted", HttpStatus.OK);
    }
    @RequestMapping(value = "/getByCity")
    public ResponseEntity<Object> getByCity(@RequestParam String location) {
        College college = collegeService.getByName(location);
        return new ResponseEntity<>(college, HttpStatus.OK);
    }
}
