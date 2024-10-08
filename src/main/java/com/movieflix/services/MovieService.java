package com.movieflix.services;



import java.util.List;

import com.movieflix.entites.Movie;


public interface MovieService
{
	public String addMovie(Movie movie);
	
	public List<Movie> viewMovie();
		
}
