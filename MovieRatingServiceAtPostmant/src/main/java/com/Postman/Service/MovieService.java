package com.Postman.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Postman.Model.Movies;
import com.Postman.Repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository repository;

	// get movie by id;
	public Movies getMovieById(int movieId) {
		return repository.findById(movieId).orElse(null);
	}
	
	
	// get all movies
	public List<Movies> getAllMovies(){
		return repository.findAll();
	}
	
	// addMovie
	public void addMovie(String movieName) {
		try {
			Movies movie = new Movies();
			movie.setTitle(movieName);
			repository.save(movie);
		} catch (Exception e) {
			throw new RuntimeException("Movie already exists");
		}
	}
	
	// update movie
	public boolean updateMovie(int id, Movies movie) {
		if(repository.existsById(id)) {
			movie.setId(id);
			repository.save(movie);
			return true;
		}
		return false;
	}
	
	// delete movie
	
	public boolean deleteMovie(int id) {
		Movies movie = repository.findById(id).orElse(null);
		if(movie==null) {
			return false;
		}
		else {
			repository.deleteById(id);
			return true;
		}
	}
	
}
