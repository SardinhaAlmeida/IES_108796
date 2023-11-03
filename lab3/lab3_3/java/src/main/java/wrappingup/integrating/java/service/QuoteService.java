package wrappingup.integrating.java.service;

import wrappingup.integrating.java.entity.Quote;

import java.util.List;

public interface QuoteService {
    Quote createQuote(Quote quote);

    Quote getQuoteByMovieId(Long movieId);

    List<Quote> getQuotesByMovie(Long movieId);

    Quote getRandomQuote();

    Quote getQuote(long quoteId);

    void deleteQuote(Long quoteId);
}