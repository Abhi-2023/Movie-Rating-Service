package com.Postman.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Postman.Model.Movies;
import com.Postman.Model.Ratings;
import com.Postman.Model.Users;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Integer> {

	Ratings findByMovieAndUser(Movies movie, Users user);

	List<Ratings> findByMovie(Movies movie);

	List<Ratings> findByUser(Users user);

}
