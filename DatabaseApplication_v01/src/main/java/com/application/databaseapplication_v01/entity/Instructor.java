package com.application.databaseapplication_v01.entity;

import com.application.databaseapplication_v01.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "instructors")
public class Instructor {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String department;

    @Column(nullable = true, length = 50)
    private String office;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Instructor() {}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Long getInstructor_id() {
        return id;
    }

    public void setInstructor_id(Long instructor_id) {
        this.id = instructor_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
