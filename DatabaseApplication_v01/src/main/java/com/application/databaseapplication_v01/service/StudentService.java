package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.entity.User;
import com.application.databaseapplication_v01.repository.RoleRepository;
import com.application.databaseapplication_v01.repository.StudentRepository;
import com.application.databaseapplication_v01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private StudentRepository studentRepo;

    public void registerStudent(Student student) {
        studentRepo.save(student);

        Role roleUser = roleRepo.findByName("STUDENT");
        User user = student.getUser();
        user.addRole(roleUser);
    }

    public void save(Student user) {
        //encodePassword(user);
        studentRepo.save(user);
    }

    public List<Student> studentList() { return studentRepo.findAll(); }

    public Student get(Long id) {
        return studentRepo.findById(id).get();
    }

    public Student validateStudent(String social, String student_id, Date birthday) {
        return studentRepo.findStudentByBirthdateAndAndStudent_idAndAndSocial(social, student_id, birthday);
    }
}
