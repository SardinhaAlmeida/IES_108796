package wrappingup.integrating.java.repository;

import wrappingup.integrating.java.entity.Quote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}