package com.celebrities.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Celebrities {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private Date birthday;
    private String gender;
    private String hobbies;
    private int inTime;
    private int outTime;

    public Celebrities(String gender, String hobbies, int inTime, int outTime) {
        this.gender = gender;
        this.hobbies = hobbies;
        this.inTime = inTime;
        this.outTime = outTime;
    }
}