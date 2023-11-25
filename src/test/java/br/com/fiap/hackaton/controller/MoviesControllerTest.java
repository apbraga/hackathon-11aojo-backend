package br.com.fiap.hackaton.controller;

import br.com.fiap.hackaton.application.MovieApplication;
import br.com.fiap.hackaton.dto.MovieDto;
import br.com.fiap.hackaton.exception.MovieNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoviesControllerTest {

    @Mock
    private MovieApplication movieApplication;

    @InjectMocks
    private MoviesController moviesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSearchMovie() {
        String searchTerm = "HACKATHON FIAP 11AOJO";

        List<MovieDto> mockMovies = Arrays.asList(new MovieDto(), new MovieDto());
        when(movieApplication.searchMovies(searchTerm)).thenReturn(mockMovies);

        ResponseEntity<List<MovieDto>> result = moviesController.searchMovie(searchTerm);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockMovies, result.getBody());
    }

    @Test
    void testGetMovie() {
        Long id = 1L;
        MovieDto mockMovie = new MovieDto();
        when(movieApplication.getMovie(id)).thenReturn(mockMovie);

        ResponseEntity<MovieDto> result = moviesController.getMovie(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockMovie, result.getBody());
    }

    @Test
    void testGetMovieNotFound() {
        Long id = 1L;
        when(movieApplication.getMovie(id)).thenThrow(new MovieNotFoundException("Treinamento não encontrado", "Não foi possível encontrar o treinamento ID: 1L"));

        assertThrows(MovieNotFoundException.class, () -> {
            moviesController.getMovie(id);
        });
    }

}
