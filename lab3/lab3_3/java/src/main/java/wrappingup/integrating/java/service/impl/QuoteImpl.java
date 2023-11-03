package wrappingup.integrating.java.service.impl;

import lombok.AllArgsConstructor;
import wrappingup.integrating.java.entity.Quote;
import wrappingup.integrating.java.repository.QuoteRepository;
import wrappingup.integrating.java.service.QuoteService;
//import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class QuoteImpl implements QuoteService {

    private QuoteRepository quoteRepository;

    @Override
    public Quote createQuote(Quote Quote) {
        return quoteRepository.save(Quote);
    }


    @Override
    public Quote getQuoteByMovieId(Long movieId) {
        Random rand = new Random();
        List<Quote> quotes = this.getQuotesByMovie(movieId);
        return quotes.get(rand.nextInt(quotes.size()));
    }

    @Override
    public List<Quote> getQuotesByMovie(Long movieId) {
        List<Quote> quotes = quoteRepository.findAll();
        quotes.removeIf(quote -> quote.getMovie_id().getId() != movieId);
        return quotes;
    }

    @Override
    public Quote getRandomQuote() {
        Random rand = new Random();
        List<Quote> quotes = quoteRepository.findAll();
        return quotes.get(rand.nextInt(quotes.size()));
    }

    @Override
    public Quote getQuote(long quoteId) {
        return quoteRepository.findById(quoteId).orElseThrow();
    }


    @Override
    public void deleteQuote(Long QuoteId) {
        quoteRepository.deleteById(QuoteId);
    }
}
