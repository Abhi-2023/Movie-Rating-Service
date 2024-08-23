package com.Postman.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Postman.Model.Movies;
import com.Postman.Service.MovieService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	@GetMapping("/all")
	public List<Movies> getAllMovies(){
		return movieService.getAllMovies();
	}
	
	// get Movie by id
	@GetMapping("/{id}")
	public Movies getMovieById(@PathVariable int id) {
		if(movieService.getMovieById( id) == null) {
			return null;
		}
		else {
			return movieService.getMovieById(id);
		}
	}
	
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addMovie(@RequestParam String title) {
		try {
			movieService.addMovie(title);
			return ResponseEntity.ok("Movie added successfully");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public String updateMovieById(@PathVariable int id, @RequestBody Movies movie) {
		boolean status = movieService.updateMovie(id, movie);
		if(status == true) {
			return "Movie updated successfully";
		}
		else {
			return "No movie found by this id";
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable int id) {
		boolean status = movieService.deleteMovie(id);
		return status == true? "Movie deleted successfully": "Movie not found by this id";
	}
	
	
}
