package com.example.lab5.controller;

import com.example.lab5.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.lab5.repository.StudentRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("student", new Student());

        model.addAttribute("programs", List.of("CSIS","MNG","BBB"));
        model.addAttribute("hobbyList", List.of("Reaqding","Gaming","Programing"));

        return "StudentForm";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "student-list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student, Model model, @RequestParam(value = "hobbiesSelected", required = false) List<String> hobbiesSelected) {
        if (hobbiesSelected != null) {
            student.setHobbies(String.join(",", hobbiesSelected));
        }
        studentRepository.save(student);
        return "redirect:/list";

    }
}
