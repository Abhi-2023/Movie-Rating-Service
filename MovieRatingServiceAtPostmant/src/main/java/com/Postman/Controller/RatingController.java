package com.Postman.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Postman.DTO.MovieDTO;
import com.Postman.Model.Ratings;
import com.Postman.Model.Users;
import com.Postman.Repository.UserRepository;
import com.Postman.Service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

	@Autowired
	RatingService ratingService;
	
	@Autowired
	UserRepository userRepository;
	
	// returns all ratings 
	@GetMapping("/allRatings/{movieId}")
	public List<MovieDTO> getAllRatingForMovie(@PathVariable int movieId){
		return ratingService.getRatingByMovieId(movieId);
	}
	
	// get rating for specific movie by specific user
	@GetMapping("/user")
    public ResponseEntity<MovieDTO> getUserRatingForMovie(@RequestParam("username") String username, @RequestParam("title") String title) {
        MovieDTO rating = ratingService.getUserRatingForMovie(username, title);
        if (rating != null) {
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
	
	@GetMapping("/allMovies/{username}")
	public List<MovieDTO> getMoviesRatedByUser(@PathVariable("username") String username){
		Users users = userRepository.findByUsername(username);
		return ratingService.getMoviesAndRatingByUser(users);
	}
	
	@PostMapping("/addRatings")
	public String addRating(@RequestParam("username") String username, @RequestParam("title") String title, @RequestParam
			("ratingValue") int ratingValue) {
		String status = ratingService.addMovieRatingByUser(title, username, ratingValue);
		if(status.equals("Ratings given successfull")) {
			return "Ratings given successfull";
		}
		else if (status.equals("User not found")){
			return "User not found";
		}
		else {
			return "Movie not found";
		}
		
	}
	
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteMovie(@RequestParam("username") String username, @RequestParam("title") String title) {
		try {
			ratingService.deleteRatingsByUser(username, title);
			return ResponseEntity.ok("Deletion Successfull");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete");
		}
	}
	
	
	
	
	
	
}
