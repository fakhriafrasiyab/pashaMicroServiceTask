package com.example.demo.services;

import com.example.demo.model.College;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class CollegeServiceImpl implements CollegeService {
    private static HashMap<Integer, College> collegeMap = new HashMap<>();
    int lastId=3;
    static {
        College adnsu = new College();
        adnsu.setId(1);
        adnsu.setUniName("ADNSU");
        adnsu.setCity("Baku");
        collegeMap.put(adnsu.getId(), adnsu);

        College mgu = new College();
        mgu.setId(2);
        mgu.setUniName("MGU");
        mgu.setCity("Moscow");
        collegeMap.put(mgu.getId(), mgu);
    }

    public College getByName(String location) {
        System.out.println(location);
        for (College col : collegeMap.values()) {
            System.out.println(col.getCity().equals(location));
            if (col.getCity().equals(location)){
                return col;
            }
        }
        return null;
    }

    @Override
    public Collection<College> getCollege() {
        return collegeMap.values();
    }

    @Override
    public void addCollege(College college) {
        college.setId(lastId);
        collegeMap.put(lastId,college);
        lastId++;
    }

    @Override
    public void updateCollege(int id, College college) {
        collegeMap.remove(id);
        college.setId(id);
        collegeMap.put(id,college);
    }

    @Override
    public void deleteCollege(int id) {
        collegeMap.remove(id);
    }
}
