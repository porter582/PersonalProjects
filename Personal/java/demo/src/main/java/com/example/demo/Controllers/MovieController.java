package com.example.demo.Controllers;

import com.example.demo.Models.Movie;
import com.example.demo.Models.MovieViewModel;
import com.example.demo.Services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/Movies/movie")
public class MovieController
{
    MovieService _movieService;

    public MovieController(MovieService movieService)
    {
        _movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Movie>> getMovies() {
        return new ResponseEntity<>(_movieService.getMovies(), HttpStatus.OK);
    }

    @GetMapping({"/{movieId}"})
    public ResponseEntity<Movie> getMoviesById(@PathVariable("movieId") Integer id) {
        return new ResponseEntity<>(_movieService.getMoviesById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> insert(@RequestBody Movie movie) {
        Movie insertedMovie = _movieService.insert(movie);
        if(insertedMovie.getId() == _movieService.getMoviesById(insertedMovie.getId()).getId())
        {
            return new ResponseEntity<>(insertedMovie, HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>(insertedMovie, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping({"/{movieId}"})
    public ResponseEntity<Movie> updateMovie(@PathVariable("movieId") Integer id, @RequestBody Movie movie) {
        Movie updatedMovie = _movieService.updateMovie(id, movie);
        if(updatedMovie == movie)
        {
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(updatedMovie, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping({"/{movieId}"})
    public ResponseEntity<Movie> deleteMovie(@PathVariable("movieId") Integer id) {
        _movieService.deleteMovie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping({"{movieAndDirector}/{movieId}"})
    public ResponseEntity<MovieViewModel> getMovieAndDirectorByMovieId(@PathVariable("movieId") Integer id) {
        MovieViewModel movieViewModel = _movieService.getMovieAndDirectorByMovieId(id);
        if(movieViewModel != null)
        {
            return new ResponseEntity<>(movieViewModel, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
