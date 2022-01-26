package com.example.demo.Services;
import com.example.demo.Models.Movie;
import com.example.demo.Models.MovieViewModel;

import java.util.ArrayList;

public interface IMovieService {
    ArrayList<Movie> getMovies();

    Movie getMoviesById(Integer id);

    Movie insert(Movie movie);

    Movie updateMovie(Integer id, Movie movie);

    void deleteMovie(Integer id);

    MovieViewModel getMovieAndDirectorByMovieId(Integer id);
}
