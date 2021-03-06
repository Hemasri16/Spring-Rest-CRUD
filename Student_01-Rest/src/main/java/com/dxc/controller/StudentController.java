package com.dxc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.beans.Student;
import com.dxc.repository.StudentRepository;



@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class StudentController {

	@Autowired
	 private StudentRepository studentRepository;
	
	
	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
				 return (List<Student>) studentRepository.findAll();
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") int studentId)
	        throws ResourceNotFoundException {
	        Student student = studentRepository.findById(studentId)
	          .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
	        return ResponseEntity.ok().body(student);
	    }
	@PostMapping("/students")
	public Student createStudent ( @RequestBody Student student)
	{
		return studentRepository.save(student);
	}
	
	@PutMapping("/students/{id}")
	 public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") int studentId,
	          @RequestBody Student studentDetails) throws ResourceNotFoundException {
	        Student student = studentRepository.findById(studentId)
	        .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

	        student.setEmail(studentDetails.getEmail());
	        student.setName(studentDetails.getName());
	        student.setId(studentDetails.getId());
	        student.setMobile(studentDetails.getMobile());
	        student.setDob(studentDetails.getDob());
	        
	        final Student updatedStudent = studentRepository.save(student);
	        return ResponseEntity.ok(updatedStudent);
	    }

	
	@DeleteMapping(  "/students/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") int studentId)
	         throws ResourceNotFoundException {
	        Student student = studentRepository.findById(studentId)
	       .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

	        studentRepository.delete(student);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	}