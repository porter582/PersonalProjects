package com.example.demo.Services;
import com.example.demo.Models.Director;
import com.example.demo.Models.Movie;
import com.example.demo.Models.MovieViewModel;
import com.example.demo.Repositories.IDirectorRepository;
import com.example.demo.Repositories.IMovieRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MovieService implements IMovieService{

    IMovieRepository _movieRepository;
    IDirectorRepository _directorRepository;

    public MovieService(IMovieRepository movieRepository, IDirectorRepository directorRepository)
    {
        _movieRepository = movieRepository;
        _directorRepository = directorRepository;
    }

    @Override
    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> movieList = new ArrayList<Movie>();
        _movieRepository.findAll().forEach(movieList::add);
        return movieList;
    }

    @Override
    public Movie getMoviesById(Integer id) {
        return _movieRepository.findById(id).get();
    }

    @Override
    public Movie insert(Movie movie) {
        return _movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Integer id, Movie movie) {
        Movie oldMovie = _movieRepository.findById(id).get();
        oldMovie.setTitle(movie.getTitle());
        oldMovie.setReviewScore(movie.getReviewScore());
        oldMovie.setDirectorId(movie.getDirectorId());
        _movieRepository.save(oldMovie);
        return getMoviesById(id);
    }

    @Override
    public void deleteMovie(Integer id) {
        _movieRepository.deleteById(id);
    }

    @Override
    public MovieViewModel getMovieAndDirectorByMovieId(Integer id) {
        Movie movie = new Movie();
        movie = _movieRepository.findById(id).get();
        Director director = new Director();
        Integer directorId = movie.getDirectorId();
        director = _directorRepository.findById(directorId).get();
        return MovieToMovieViewModelMapper(movie, director);
    }

    public MovieViewModel MovieToMovieViewModelMapper(Movie movie, Director director)
    {
        MovieViewModel movieViewModel = new MovieViewModel();
        movieViewModel.DirectorName = director.getFirstName() + " " + director.getLastName();
        movieViewModel.Title = movie.getTitle();
        movieViewModel.ReviewScore = movie.getReviewScore();
        return movieViewModel;
    }
}
