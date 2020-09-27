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

import com.dxc.beans.Exam;
import com.dxc.repository.ExamRepository;



@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class ExamController {

	@Autowired
	 private ExamRepository examRepository;
	
	
	@GetMapping("/exam")
	public List<Exam> getAllExam()
	{
				 return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/exam/{id}")
	public ResponseEntity<Exam> getExamById(@PathVariable(value = "id") int examId)
	        throws ResourceNotFoundException {
	        Exam exam =examRepository.findById(examId)
	          .orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));
	        return ResponseEntity.ok().body(exam);
	    }
	@PostMapping("/exam")
	public Exam createExam ( @RequestBody Exam exam)
	{
		return examRepository.save(exam);
	}
	
	@PutMapping("/exam/{id}")
	 public ResponseEntity<Exam> updateExam(@PathVariable(value = "id") int examId,
	          @RequestBody Exam examDetails) throws ResourceNotFoundException {
	        Exam exam= examRepository.findById(examId)
	        .orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));

	        exam.setId(examDetails.getId());	        
	       exam.setName(examDetails.getName());
	        final Exam updatedExam = examRepository.save(exam);
	        return ResponseEntity.ok(updatedExam);
	    }

	
	@DeleteMapping(  "/exam/{id}")
	public Map<String, Boolean> deleteExam(@PathVariable(value = "id") int examId)
	         throws ResourceNotFoundException {
	       Exam exam = examRepository.findById(examId)
	       .orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));

	        examRepository.delete(exam);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	}