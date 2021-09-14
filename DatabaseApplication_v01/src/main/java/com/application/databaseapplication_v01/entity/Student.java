package com.application.databaseapplication_v01.entity;

import com.application.databaseapplication_v01.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String grade_level;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    @Column(nullable = true, length = 80)
    private String major;

    @Column(nullable = true, length = 80)
    private String minor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public Student() {}

    public Student(String grade_level) {
        this.grade_level = grade_level;
    }

    public String getGrade_level() {
        return grade_level;
    }

    public void setGrade_level(String grade_level) {
        this.grade_level = grade_level;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public Long getStudent_id() {
        return id;
    }

    public void setStudent_id(Long student_id) {
        this.id = student_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean equals(User user) {
        return this.getUser().getId() == user.getId();
    }
}
