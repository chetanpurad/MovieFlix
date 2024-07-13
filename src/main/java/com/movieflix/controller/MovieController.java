package com.movieflix.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.movieflix.entites.Movie;
import com.movieflix.services.MovieService;

@Controller
public class MovieController 
{
	@Autowired
	MovieService ser;
	
	@PostMapping("addmovie")
	public String addMovie(@ModelAttribute Movie movie ,Model model)
	{
		ser.addMovie(movie);
		model.addAttribute("message","Movie added Success");
		return "addmovie";
	}
	
	@GetMapping("viewMovie")
	public String viewMovie(Model model)
	{
		List<Movie> list=ser.viewMovie();
		model.addAttribute("movies", list);
		return "viewmovie";
	}
}

