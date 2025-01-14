package com.techreturners.movieApi.controller;

import com.techreturners.movieApi.service.WatchListService;
import com.techreturners.movieApi.vo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/watchlist")
public class WatchListController {
    @Autowired
    private WatchListService watchListService;

    @GetMapping("movies")
    public ResponseEntity<?> getMovies() {
        List<Movie> movies;
        try {
            movies = watchListService.getMovieDetails();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Retrieving Movies");
        }
    }

    @PostMapping
    public ResponseEntity<Movie> saveMoviesToWatchList(@RequestBody Movie movie) {
        try {
           watchListService.saveMovies(movie);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("{imdbId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("imdbId") String imdbId) {
        try {
            watchListService.deleteMovieByImdbId(imdbId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


