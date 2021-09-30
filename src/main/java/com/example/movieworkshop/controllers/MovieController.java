package com.example.movieworkshop.controllers;

import com.example.movieworkshop.models.Movie;
import com.example.movieworkshop.services.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class MovieController {

    private MovieService movieService = new MovieService();

    public MovieController() throws FileNotFoundException {
    }

    //3.1 This end-point welcomes the user and prints out a short description of your
    //application
    @GetMapping("/")
    public String index() {
        return "<center>Welcome, friend!<center>";
    }

    //3.2 /getFirst
    @GetMapping("/getFirst")
    public String getFirst() {
        return movieService.getFirst();
    }

    //3.3 /getRandom
    @GetMapping("/getRandom")
    public String getRandom() {
        return movieService.getRandom();
    }

    //3.4 /getTenSortByPopularity
    @GetMapping("/getTenSortByPopularity")
    public String getTenSortByPopularity() {
        return movieService.getTenSortByPopularity();
    }

    //3.5 /howManyWonAnAward
    @GetMapping("/howManyWonAnAward")
    public String howManyWonAnAward() {
        return movieService.howManyWonAnAward();
    }

    //3.6 (Advanced) /filter
    //http://localhost:8080/filter?character=e&count=2
    @GetMapping("/filter")
    public String filter(@RequestParam char character, @RequestParam int count){
        return movieService.filter('e', 2);
    }

    //3.7 (Advanced) /longest
    //http://localhost:8080/longest?genre1=action&genre2=comedy
    @GetMapping("/longest")
    public String longest(@RequestParam String genre1, @RequestParam String genre2){
        return movieService.longest("action", "comedy");
    }

    //advanced SQL
    @GetMapping("/displayAwardMovies")
    public String displayAwardMovies() throws FileNotFoundException, SQLException {
        return movieService.displayAwardMovies("comedy");
    }

}
