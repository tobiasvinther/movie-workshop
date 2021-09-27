package com.example.movieworkshop.services;

import com.example.movieworkshop.models.Movie;
import com.example.movieworkshop.repositories.MovieDataRepository;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MovieService {

    MovieDataRepository movieDataRepository = new MovieDataRepository();

    public MovieService() throws FileNotFoundException {
    }

    //3.2 Shown in class This end-points calls a service that finds the first movie from
    //the list and displays the title.
    public String getFirst() {
        return movieDataRepository.getMovieList().get(0).toString();
    }


    //3.3 This end-point calls a service, that finds a single random movie from the list
    //and displays the title.
    public String getRandom() {
        Random random = new Random();
        int randomMovieIndex = random.nextInt(movieDataRepository.getMovieList().size());
        Movie randomMovie = movieDataRepository.getMovieList().get(randomMovieIndex);
        return randomMovie.getTitle();
    }

    //3.4 /getTenSortByPopularity
    //fetches 10 random movies,
    //maps each result to a Movie model class,
    //adds to a Movie Arraylist
    //prints the result to the browser - sorted in ascending order by popularity (Hint: Remember the comparable interface).
    public String getTenSortByPopularity() {
        ArrayList<Movie> randomMovieList = new ArrayList<>();
        Random random = new Random();

        //find and add 10 random movies to the list
        for(int i=0; i < 10; i++) {
            int randomMovieIndex = random.nextInt(movieDataRepository.getMovieList().size());
            Movie randomMovie = movieDataRepository.getMovieList().get(randomMovieIndex);
            //if the movie is already in the list, try again
            if(!randomMovieList.contains(randomMovie)) {
                randomMovieList.add(randomMovie);
            } else {
                i--;
            }
        }

        Collections.sort(randomMovieList);

        //create a string that can print somewhat nicely
        String resultString = "10 random movies:<br>";
        int counter = 1;
        for(Movie movie : randomMovieList) {
            resultString = resultString + counter + ". " + movie.getTitle() + " "
                    + "(popularity: " + movie.getPopularity() + ")<br>";
            counter++;
        }

        return resultString;
    }

    //3.5 This end-point prints how many of the movies of the data-set that won an award
    public String howManyWonAnAward() {
        int awardCounter = 0;
        for (Movie movie : movieDataRepository.getMovieList()) {
            if(movie.isHasAwards()) {
                awardCounter++;
            }
        }
        return "Number of movies that has won an award: " + awardCounter;
    }

    //3.6 This end points calls a service that prints all movies, but only if they contain x
    //character n amount of times
    public String filter(char character, int amount) {
        ArrayList<Movie> filteredMovieList = new ArrayList<>();
        for (Movie movie : movieDataRepository.getMovieList()) {
            int charCounter = 0;
            for(char c : movie.getTitle().toCharArray()) {
                if(c == character) {
                    charCounter++;
                }
            }
            if(charCounter == amount) {
                filteredMovieList.add(movie);
            }
        }
        String resultString = "Movies with the letter '" + character +"' " + amount + " times:<br>" ;
        for(Movie movie : filteredMovieList) {
            resultString = resultString + movie.getTitle() + "<br>";

        }
        return resultString;

    }

}
