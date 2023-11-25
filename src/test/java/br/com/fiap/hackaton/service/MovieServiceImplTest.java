package br.com.fiap.hackaton.service;

import br.com.fiap.hackaton.exception.MovieNotFoundException;
import br.com.fiap.hackaton.model.Movie;
import br.com.fiap.hackaton.repository.MovieRepository;
import br.com.fiap.hackaton.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MovieServiceImplTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindMovie() {
        String query = "HACKATHON FIAP 11AOJO";

        Movie movie = new Movie();

        movie.setTitle(query);
        movie.setDescription(query);

        when(movieRepository.findMoviesByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query))
                .thenReturn(Arrays.asList(movie));

        List<Movie> result = movieService.findMovie(query);


        assertEquals(Arrays.asList(movie), result);
    }

    @Test
    void testFindMovieThrowsNotFoundException() {
        String query = "HACKATHON FIAP 11AOJO";;

        when(movieRepository.findMoviesByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query))
                .thenReturn(new ArrayList<>());

        assertThrows(MovieNotFoundException.class, () -> movieService.findMovie(query));
    }

    @Test
    void testGetMovie() {
        Long id = 1L;
        Movie movie = new Movie();

        when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovie(id);

        assertSame(movie, result);
    }

    @Test
    void testGetMovieThrowsNotFoundException() {
        Long id = 1L;
        when(movieRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class, () -> movieService.getMovie(id));
    }

}
