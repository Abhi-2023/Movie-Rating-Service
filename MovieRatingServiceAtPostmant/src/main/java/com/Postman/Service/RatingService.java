package com.Postman.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Postman.DTO.MovieDTO;
import com.Postman.Model.Movies;
import com.Postman.Model.Ratings;
import com.Postman.Model.Users;
import com.Postman.Repository.MovieRepository;
import com.Postman.Repository.RatingRepository;
import com.Postman.Repository.UserRepository;

@Service
public class RatingService {
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	// Add rating for the movie by specific user ..............
	public String addMovieRatingByUser(String title, String username, int ratingValue) {
		Users user = userRepository.findByUsername(username);
		Movies movie = movieRepository.findByTitle(title);
		if(user != null && movie != null) {
			Ratings rating = new Ratings();
			rating.setRatingValue(ratingValue);
			rating.setMovie(movie);
			rating.setUsers(user);
			ratingRepository.save(rating);
			return "Ratings given successfull";
		}
		else if(user == null) {
			return "User not found";
		}
		else{
			return "Movie Not found";
		}
	}
	
	// get all ratings for specific movieId ..........
	public List<MovieDTO> getRatingByMovieId(int movieId ){
		Movies movie = movieRepository.findById(movieId).orElse(null);
		if(movie != null) {
			System.out.println(movie.getTitle());
			 return movie.getRatings().stream().map(ratings -> new MovieDTO(movie.getId(), movie.getTitle(),ratings.getUsers().getUsername(), 
					ratings.getRatingValue())).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
	
	
	// geting ratings rated by user to specific movie ...........
	public MovieDTO getUserRatingForMovie(String username, String title) {
		Users user = userRepository.findByUsername(username);
		Movies movie = movieRepository.findByTitle(title);
		if(user != null && movie!= null) {
			Ratings ratings = ratingRepository.findByMovieAndUser(movie, user);
			return new MovieDTO(user.getId(), movie.getTitle(), user.getUsername(), ratings.getRatingValue()) ;
		}
		return null;
	}
	
	// getting all movies along with ratings rated by specific user ..........
	public List<MovieDTO> getMoviesAndRatingByUser(Users user){
		List<Ratings> userRatings = ratingRepository.findByUser(user);
		return userRatings.stream().map(rating -> new MovieDTO(rating.getMovie().getId(), rating.getMovie().getTitle(),
				rating.getUsers().getUsername(), rating.getRatingValue())
		) 
		.collect(Collectors.toList());
	}
	// update the rating to specific movie rated by specific user
//	public void updateMoveRatedByUser(String title, String username, int ratingValue) {
//		Movies movie = movieRepository.findByTitle(title);
//		Users users = userRepository.findByUsername(username);
//		if(users!=null && movie != null) {
//			Ratings ratings = ratingRepository.findByMovieAndUser(movie, users);
//					if(ratings != null) {
//						ratings.setRatingValue(ratingValue);
//						ratingRepository.save(ratings);
//					}
//		}
//		
//	}
//	
	// delete rating for specific by specific user
	public void deleteRatingsByUser(String username, String title) {
		Movies movie = movieRepository.findByTitle(title);
		Users users = userRepository.findByUsername(username);
		if(users!=null && movie != null) {
			Ratings ratings = ratingRepository.findByMovieAndUser(movie, users);
					if(ratings != null) {
						ratingRepository.delete(ratings);
					}
		}
	}

}
