package com.example.movieworkshop.database;

import com.example.movieworkshop.models.Movie;
import com.example.movieworkshop.repositories.MovieDataRepository;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;

public class JDBCwriter1 {

    private static Connection connection;
    private MovieDataRepository movieDataRepository = new MovieDataRepository();

    public JDBCwriter1() throws FileNotFoundException {
        connection = getConnection();
    }

    public Connection getConnection() {
        final String url = "jdbc:mysql://localhost:3306/movie_workshop";
        try {
            connection = DriverManager.getConnection(url, "simpleUser", "hej123");
            System.out.println("Successfully connected to database");
            return connection;
        } catch (SQLException error) {
            System.out.println("Couldn't connect to database. Error: " + error);
            return null;
        }
    }

    public void populateDatabase() {
        //delete data, then repopulate. Activate this is you need to re-import data
        /*
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("TRUNCATE TABLE movies");
            deleteStatement.execute();
        } catch(SQLException error) {
            System.out.println("Error when deleting database: " + error.getMessage());
        }
         */

        String insertString = "INSERT INTO movies (movie_id, title, genre, has_award) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement;
        int moviesInserted = 0;

        try {
            for(Movie movie : movieDataRepository.getMovieList()) {

                preparedStatement = connection.prepareStatement(insertString);
                preparedStatement.setInt(1, moviesInserted);
                preparedStatement.setString(2, movie.getTitle());
                preparedStatement.setString(3, movie.getSubject());
                preparedStatement.setBoolean(4, movie.isHasAwards());
                preparedStatement.execute();
                moviesInserted++;
            }

        } catch(SQLException error) {
            System.out.println("Error when populating database: " + error.getMessage());
        }
        System.out.println("Finished populating database: " + moviesInserted);
    }

    public String getMoviesWithAwards(String genre) {
        String selectString = "SELECT title FROM movies WHERE movies.genre = '" + genre + "' AND has_award = 1";
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(selectString);
            resultSet = preparedStatement.executeQuery();
            return resultSetToString(resultSet);
        } catch (SQLException error) {
            error.printStackTrace();
        }
        System.out.println("Something went wrong with getMoviesWithAwards");
        return null;
    }

    public String resultSetToString(ResultSet resSetToPrint) throws SQLException {
        String returnString = "";
        while (resSetToPrint.next()) {
                returnString = returnString + " " + resSetToPrint.getString("title") + "<br>";
            }
        return returnString;
        }

}

