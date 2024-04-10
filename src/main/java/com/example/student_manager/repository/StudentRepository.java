package com.example.student_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_manager.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	
}
