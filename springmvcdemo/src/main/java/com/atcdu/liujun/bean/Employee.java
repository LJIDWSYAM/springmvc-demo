package com.atcdu.liujun.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    public Date getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return "Eeployee{" +
                "id=" + id +
                ", birth=" + birth +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @NotNull(message = "不能为空")
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date birth;
    @NotEmpty
    private String lastName;
    @Email
    private String email;
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
