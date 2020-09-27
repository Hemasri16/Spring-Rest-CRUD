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

import com.dxc.beans.Marks;
import com.dxc.repository.MarksRepository;



@RestController @CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class MarksController {

	@Autowired
	 private MarksRepository marksRepository;
	
	
	@GetMapping("/marks")
	public List<Marks> getAllMarks()
	{
				 return (List<Marks>) marksRepository.findAll();
	}
	
	@GetMapping("/marks/{id}")
	public ResponseEntity<Marks> getMarksById(@PathVariable(value = "id") int marksId)
	        throws ResourceNotFoundException {
	        Marks marks = marksRepository.findById(marksId)
	          .orElseThrow(() -> new ResourceNotFoundException("Marks not found for this id :: " + marksId));
	        return ResponseEntity.ok().body(marks);
	    }
	@PostMapping("/marks")
	public Marks createMarks ( @RequestBody Marks marks)
	{
		return marksRepository.save(marks);
	}
	
	@PutMapping("/marks/{id}")
	 public ResponseEntity<Marks> updateMarks(@PathVariable(value = "id") int marksId,
	          @RequestBody Marks marksDetails) throws ResourceNotFoundException {
	        Marks marks= marksRepository.findById(marksId)
	        .orElseThrow(() -> new ResourceNotFoundException("Marks not found for this id :: " + marksId));

	        marks.setStudid(marksDetails.getStudid());
	        marks.setExamid(marksDetails.getExamid());
	        marks.setSub1(marksDetails.getSub1());
	        marks.setSub2(marksDetails.getSub2());
	        marks.setSub3(marksDetails.getSub3());
	        marks.setStud_name(marksDetails.getStud_name());
	        marks.setExam_name(marksDetails.getExam_name());
	        
	        
	        final Marks updatedMarks = marksRepository.save(marks);
	        return ResponseEntity.ok(updatedMarks);
	    }

	
	@DeleteMapping(  "/marks/{id}")
	public Map<String, Boolean> deleteMarks(@PathVariable(value = "id") int marksId)
	         throws ResourceNotFoundException {
	        Marks marks = marksRepository.findById(marksId)
	       .orElseThrow(() -> new ResourceNotFoundException("Marks not found for this id :: " + marksId));

	        marksRepository.delete(marks);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	}