package wrappingup.integrating.java.repository;

import wrappingup.integrating.java.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
