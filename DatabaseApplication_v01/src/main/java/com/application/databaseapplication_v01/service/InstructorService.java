package com.application.databaseapplication_v01.service;

import com.application.databaseapplication_v01.entity.Instructor;
import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.repository.InstructorRepository;
import com.application.databaseapplication_v01.repository.RoleRepository;
import com.application.databaseapplication_v01.repository.StudentRepository;
import com.application.databaseapplication_v01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private InstructorRepository instructorRepo;

    public void registerStudent(Instructor user) {
        Role roleUser = roleRepo.findByName("INSTRUCTOR");
        //user.addRole(roleUser);
        //encodePassword(user);
        instructorRepo.save(user);
    }

    public void save(Instructor user) {
        //encodePassword(user);
        instructorRepo.save(user);
    }
}
