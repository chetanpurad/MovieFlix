package com.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movieflix.entites.Movie;
import com.movieflix.repositories.MovieRepo;

@Service
public class MovieServiceimplements implements MovieService
{
	@Autowired
	MovieRepo movierepo;
	
	@Override
	public String addMovie(Movie movie) {
		movierepo.save(movie);
		return "movie added";
	}

	@Override
	public List<Movie> viewMovie() {
		List<Movie> list=movierepo.findAll();
		return list;
		
		
	}
	

}
