package wrappingup.integrating.java.service.impl;

import lombok.AllArgsConstructor;
import wrappingup.integrating.java.entity.Movie;
import wrappingup.integrating.java.repository.MovieRepository;
import wrappingup.integrating.java.service.MovieService;
//import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import java.util.List;
//import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieImpl implements MovieService {

    private MovieRepository movieRepository;

    @Override
    public Movie createMovie(Movie Movie) {
        return movieRepository.save(Movie);
    }

    @Override
    public Movie getMovieById(Long movieId) {
        Optional<Movie> optionalUser = movieRepository.findById(movieId);
        return optionalUser.get();
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie updateMovie(Movie Movie) {
        Movie existingMovie = movieRepository.findById(Movie.getId()).get();
        existingMovie.setTitle(Movie.getTitle());
        existingMovie.setYear(Movie.getYear());
        Movie updatedMovie = movieRepository.save(existingMovie);
        return updatedMovie;
    }

    @Override
    public void deleteMovie(Long MovieId) {
        movieRepository.deleteById(MovieId);
    }
}
