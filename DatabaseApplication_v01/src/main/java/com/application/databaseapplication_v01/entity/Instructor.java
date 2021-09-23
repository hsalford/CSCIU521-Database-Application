package com.application.databaseapplication_v01.entity;

import com.application.databaseapplication_v01.entity.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "instructors")
public class Instructor {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 150)
    private String department;

    @Column(nullable = true, length = 50)
    private String office;

    @OneToMany(mappedBy = "instructor")
    private Set<CourseInstructor> courseInstructors;

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

    public void setCourseInstructors(Set<CourseInstructor> courseInstructors) {
        this.courseInstructors = courseInstructors;
    }

    public Set<CourseInstructor> getCourseInstructors() {
        return courseInstructors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
