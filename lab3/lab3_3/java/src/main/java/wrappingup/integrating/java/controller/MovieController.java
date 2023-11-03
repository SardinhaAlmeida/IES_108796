package wrappingup.integrating.java.controller;

import lombok.AllArgsConstructor;
import wrappingup.integrating.java.entity.Movie;
import wrappingup.integrating.java.entity.Quote;
import wrappingup.integrating.java.service.QuoteService;
import wrappingup.integrating.java.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class MovieController {

    private QuoteService quoteService;
    private MovieService movieService;


    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
      return movieService.createMovie(movie);
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") Long movieId) {
      return movieService.getMovieById(movieId);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
      return movieService.getAllMovies();
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long movieId,
                                           @RequestBody Movie movie){
        movie.setId(movieId);
        Movie updatedMovie = movieService.updateMovie(movie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long movieId){
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Movie successfully deleted!", HttpStatus.OK);
    }


    @PostMapping("/quote")
    public Quote creatQuote(@RequestBody Quote quote) {
      return quoteService.createQuote(quote);
    }

    @GetMapping("/quote")
    public Quote getRandomQuote(@RequestParam(value = "movie", required = false) Long movieId) {
      if (movieId != null)
        return quoteService.getQuoteByMovieId(movieId);
      return quoteService.getRandomQuote();
    }

    @GetMapping("/quotes")
    public List<Quote> getQuotesByMovie(@RequestParam(value = "movie", defaultValue = "") long movieId) {
      return quoteService.getQuotesByMovie(movieId);
    }

    @GetMapping("/quote/{id}")
    public Quote getQuote(@PathVariable(value = "id") Long quoteId) {
      return quoteService.getQuote(quoteId);
    }

    @DeleteMapping("/quote/{id}")
    public ResponseEntity<String> deleteQuote(@PathVariable("id") Long quoteId){
        quoteService.deleteQuote(quoteId);
        return new ResponseEntity<>("Quote successfully deleted!", HttpStatus.OK);
    }
}