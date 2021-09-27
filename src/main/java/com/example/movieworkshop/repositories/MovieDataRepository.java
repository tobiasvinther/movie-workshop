package com.example.movieworkshop.repositories;

import com.example.movieworkshop.models.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieDataRepository {

    private File movies = new File("resources/imdb-data.csv"); //create a reference to the csv file
    private Scanner fileScanner = new Scanner(movies); //
    private ArrayList<Movie> movieList = new ArrayList<>();

    public MovieDataRepository() throws FileNotFoundException {

        fileScanner.nextLine(); //ignore first line of file

        //while there is something left to read, continue
        while(fileScanner.hasNext()) {

            String currentString = fileScanner.nextLine(); // a string that holds the current String. We read the file line by line.
            //System.out.println(currentString); // print the current string (currentString)

            String[] lineAsArray = currentString.split(";"); //split currentString using ";" as separator indicator

            //create variables for each character datum, assign the from the array and
            // create Character objects from these values. Then add them to the arrayList
            int year = tryParseInt(lineAsArray[0]);
            int length = tryParseInt(lineAsArray[1]);
            String title = lineAsArray[2];
            String subject = lineAsArray[3];
            int popularity = tryParseInt(lineAsArray[4]);
            boolean hasAwards = translateToBoolean(lineAsArray[5]);

            Movie tempMovie = new Movie(year, length, title, subject,
                    popularity, hasAwards);

            movieList.add(tempMovie);

        }
    }

    public static int tryParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public boolean translateToBoolean(String text) {
        if(text.equalsIgnoreCase("no")) {
            return false;
        } else return true;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }
}
