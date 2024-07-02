package com.celebrities.service.impl;

import com.celebrities.dao.CelebritiesDao;
import com.celebrities.modal.Celebrities;
import com.celebrities.service.CelebritiesService;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CelebritiesServiceImpl implements CelebritiesService {

    @Autowired
    CelebritiesDao celebritiesDao;

    @Autowired
    HikariDataSource dataSource;

    @Override
    public List<Celebrities> getCelebritiesDetails(Logger log) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            return  celebritiesDao.getCelebritiesDetails(con, log);
        } catch(Exception ex){
            log.error("Error while getting celebrities: " + ex.getStackTrace());
        } finally {
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (Exception ex){
                log.error("Error while getting celebrities: " + ex.getStackTrace());
            }
        }
        return null;
    }

    @Override
    public String saveCelebritiesDetails(Celebrities celebrities, Logger log) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            if(celebritiesDao.saveCelebritiesDetails(celebrities, con, log) != 1){
                return "Error while saving details";
            } else {
                return "Celebrities added successfully";
            }
        } catch(Exception ex){
            log.error("Error while getting celebrities: " + ex.getStackTrace());
            return "Error while saving details";
        } finally {
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (Exception ex){
                log.error("Error while getting celebrities: " + ex.getStackTrace());
            }
        }
    }

    @Override
    public String updateCelebritiesDetails(Celebrities celebrities, Logger log) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            if(celebritiesDao.updateCelebritiesDetails(celebrities, con, log) != 1){
                return "Error while updating details";
            } else {
                return "Celebrities details updated successfully";
            }
        } catch(Exception ex){
            log.error("Error while getting celebrities: " + ex.getStackTrace());
            return "Error while updating details";
        } finally {
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (Exception ex){
                log.error("Error while getting celebrities: " + ex.getStackTrace());
            }
        }
    }

    @Override
    public String getBestTime(Logger log){
        Connection con = null;
        try {
            con = dataSource.getConnection();
            List<Celebrities> celebritiesList = celebritiesDao.getCelebritiesDetails(con, log);

//            List<Celebrities> celebritiesList = new ArrayList<>();
//            celebritiesList.add(new Celebrities("Male", "Sports", 6, 8));
//            celebritiesList.add(new Celebrities("Female", "Reading", 7, 10));
//            celebritiesList.add(new Celebrities("Male", "Gaming", 6, 7));
//            celebritiesList.add(new Celebrities("Female", "Music", 8, 11));
//            celebritiesList.add(new Celebrities("Male", "Cooking", 7, 10));

            int minIn = Integer.MAX_VALUE;
            int maxOut = Integer.MIN_VALUE;
            for (Celebrities celebrity : celebritiesList) {
                minIn = Math.min(minIn, celebrity.getInTime());
                maxOut = Math.max(maxOut, celebrity.getOutTime());
            }

            Map<Integer, Integer> valueMap = new HashMap<>();
            for (int i = minIn; i < maxOut; i++) {
                valueMap.put(i, 0);
            }

            for (Celebrities celebrity : celebritiesList) {
                for (int i = celebrity.getInTime(); i < celebrity.getOutTime(); i++) {
                    if (valueMap.containsKey(i)) {
                        valueMap.put(i, valueMap.get(i) + 1);
                    }
                }
            }

            int maxValue = Integer.MIN_VALUE;
            for (int value : valueMap.values()) {
                maxValue = Math.max(maxValue, value);
            }

            List<Integer> keysWithMaxValue = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : valueMap.entrySet()) {
                if (entry.getValue() == maxValue) {
                    keysWithMaxValue.add(entry.getKey());
                }
            }

            String keysAsString = keysWithMaxValue.stream()
                    .map(String::valueOf) // Convert Integer to String
                    .collect(Collectors.joining(", "));
            return "Best time: " + keysAsString;
        } catch (Exception ex){
            ex.getStackTrace();
            return null;
        } finally {
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (Exception ex){
                log.error("Error while getting celebrities: " + ex.getStackTrace());
            }
        }
    }

}