package com.example.student_manager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.student_manager.model.Student;
import com.example.student_manager.repository.StudentRepository;

@Controller
public class StudentController {

	
	
	@Autowired
    private StudentRepository studentRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("studenti", studentRepository.findAll());
        return "index.html";
    }

    @GetMapping("/add")
    public String addStudentForm( @RequestBody Student student) {
    	studentRepository.save(student)    ;  
    	return "add-students.html";
    }

    @PostMapping("/add")
    public String addStudentSubmit(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            model.addAttribute("student", student);
            return "edit-students.html";
        } else {
            // Gestione del caso in cui lo studente non viene trovato
            return "redirect:/";
        }
    }


    @PostMapping("/edit/{id}")
    public String editStudentSubmit(@PathVariable Long id, @ModelAttribute Student student) {
        student.setId(id);
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/";
    }
}
	

