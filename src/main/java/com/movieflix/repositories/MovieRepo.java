package com.movieflix.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieflix.entites.Movie;

public interface MovieRepo extends JpaRepository<Movie, Integer>
{

}
