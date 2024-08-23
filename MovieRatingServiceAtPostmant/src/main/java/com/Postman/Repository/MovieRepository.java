package com.Postman.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Postman.Model.Movies;

@Repository
public interface MovieRepository extends JpaRepository<Movies, Integer>{

	Movies findByTitle(String title);

}
