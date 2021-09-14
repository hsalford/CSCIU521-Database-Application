package com.application.databaseapplication_v01.repository;

import com.application.databaseapplication_v01.entity.Role;
import com.application.databaseapplication_v01.entity.Student;
import com.application.databaseapplication_v01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
