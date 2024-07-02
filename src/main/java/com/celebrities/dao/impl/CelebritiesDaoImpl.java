package com.celebrities.dao.impl;

import com.celebrities.dao.CelebritiesDao;
import com.celebrities.modal.Celebrities;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CelebritiesDaoImpl implements CelebritiesDao {

    @Override
    public List<Celebrities> getCelebritiesDetails(Connection con, Logger log){
        List<Celebrities> celebritiesList = new ArrayList<>();
        try {
            String query = "SELECT id, first_name, last_name, email, phone, address, city, state, country, birthday, gender, hobbies, in_time, out_time " +
                    " FROM \"Celebrity\".celebrities;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                Celebrities celebrities = new Celebrities();
                celebrities.setId(rs.getInt("id"));
                celebrities.setFirstName(rs.getString("first_name"));
                celebrities.setLastName(rs.getString("last_name"));
                celebrities.setEmail(rs.getString("email"));
                celebrities.setPhone(rs.getString("phone"));
                celebrities.setAddress(rs.getString("address"));
                celebrities.setCity(rs.getString("city"));
                celebrities.setState(rs.getString("state"));
                celebrities.setCountry(rs.getString("country"));
                celebrities.setBirthday(rs.getDate("birthday"));
                celebrities.setGender(rs.getString("gender"));
                celebrities.setHobbies(rs.getString("hobbies"));
                celebrities.setInTime(rs.getInt("in_time"));
                celebrities.setOutTime(rs.getInt("out_time"));
                celebritiesList.add(celebrities);
            }
        } catch(Exception ex){
            log.error(" Error while getting celebrities list: " + ex.getStackTrace());
        }
        return celebritiesList;
    }

    @Override
    public int saveCelebritiesDetails(Celebrities celebrities, Connection con, Logger log){
        int result = 0;
        try {
            String query = "INSERT INTO \"Celebrity\".celebrities " +
                    "(first_name, last_name, email, phone, address, city, state, country, birthday, gender, hobbies, in_time, out_time, created_date)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now);";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, celebrities.getFirstName());
            prepStatement.setString(2, celebrities.getLastName());
            prepStatement.setString(3, celebrities.getEmail());
            prepStatement.setString(4, celebrities.getPhone());
            prepStatement.setString(5, celebrities.getAddress());
            prepStatement.setString(6, celebrities.getCity());
            prepStatement.setString(7, celebrities.getState());
            prepStatement.setString(8, celebrities.getCountry());
            prepStatement.setDate(9, celebrities.getBirthday());
            prepStatement.setString(10, celebrities.getGender());
            prepStatement.setString(11, celebrities.getHobbies());
            prepStatement.setInt(12, celebrities.getInTime());
            prepStatement.setInt(13, celebrities.getOutTime());

            result = prepStatement.executeUpdate();
        } catch(Exception ex){
            log.error(" Error while saving Celebrity details: " + ex.getStackTrace());
        }
        return result;
    }

    @Override
    public int updateCelebritiesDetails(Celebrities celebrities, Connection con, Logger log){
        int result = 0;
        try {
            String query = " UPDATE \"Celebrity\".celebrities " +
                    "SET first_name=?, last_name=?, email=?, phone=?, address=?, city=?, state=?, country=?, birthday=?, gender=?, hobbies=?, in_time=?, out_time=?, modified_date= now() " +
                    "WHERE id= ?;";
            PreparedStatement prepStatement = con.prepareStatement(query);
            prepStatement.setString(1, celebrities.getFirstName());
            prepStatement.setString(2, celebrities.getLastName());
            prepStatement.setString(3, celebrities.getEmail());
            prepStatement.setString(4, celebrities.getPhone());
            prepStatement.setString(5, celebrities.getAddress());
            prepStatement.setString(6, celebrities.getCity());
            prepStatement.setString(7, celebrities.getState());
            prepStatement.setString(8, celebrities.getCountry());
            prepStatement.setDate(9, celebrities.getBirthday());
            prepStatement.setString(10, celebrities.getGender());
            prepStatement.setString(11, celebrities.getHobbies());
            prepStatement.setInt(12, celebrities.getInTime());
            prepStatement.setInt(13, celebrities.getOutTime());
            prepStatement.setInt(14, celebrities.getId());
            result = prepStatement.executeUpdate();
        } catch(Exception ex){
            log.error(" Error while saving Celebrity details: " + ex.getStackTrace());
        }
        return result;
    }
}
