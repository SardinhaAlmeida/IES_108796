package wrappingup.integrating.java.service;

import wrappingup.integrating.java.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);

    Movie getMovieById(Long movieId);

    List<Movie> getAllMovies();

    Movie updateMovie(Movie movie);

    void deleteMovie(Long movieId);
}
