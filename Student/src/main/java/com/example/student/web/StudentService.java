package com.example.student.web;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable; // keep this one
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repo;
    public List<Student> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return repo.findAll();
        }
        return repo.findByNameContainingIgnoreCase(keyword);
    }
    public Student get(Long id) { return repo.findById(id).orElseThrow();
    }
    public Student save(Student s) { return repo.save(s); }
    public void delete(Long id) { repo.deleteById(id); }
}