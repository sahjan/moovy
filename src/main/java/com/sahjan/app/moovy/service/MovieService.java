package com.sahjan.app.moovy.service;
import com.sahjan.app.moovy.payload.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MovieService {

    private List<Movie> movieList;

    @Autowired
    public MovieService() {
    }

    public void setMovieList(List<Movie> movies) {
        this.movieList = movies;
    }

    /***
     * Returns the 5 movies most appropriate for user's chosen mood
     * @param ac value of Agitated-Calm slider
     * @param hs value of Happy-Sad slider
     * @param twa value of Tired-Wide awake slider
     * @param sf value of Scared-Fearless slider
     * @return the list of 5 selected Movies
     * @throws NoSuchFileException
     */
    public List<Movie> selectMovies(int ac, int hs, int twa, int sf) throws NoSuchFileException {
        if (movieList != null) {
            List<Movie> selectedMovies = new ArrayList<>();
            //clear all elements from previous request
            selectedMovies.clear();

            //work out difference between slider values & movie values
            //and set the total difference value for the movie
            for (Movie movie : movieList) {
                int acDiff = Math.abs(movie.getAcValue() - ac);
                int hsDiff = Math.abs(movie.getHsValue() - hs);
                int twaDiff = Math.abs(movie.getTwaValue() - twa);
                int sfDiff = Math.abs(movie.getSfValue() - sf);
                int totalDiff = acDiff + hsDiff + twaDiff + sfDiff;
                movie.setTotalDiff(totalDiff);
            }

            //sort the movies by total difference, ascending
            movieList.sort(Comparator.comparingInt(Movie::getTotalDiff));

            //then go through arraylist and store the first 5 movies = the 5 least differences = movies most appropriate to user's mood
            for (int i = 0; i < 5; i++) {
                if (i < movieList.size()) {
                    selectedMovies.add(movieList.get(i));
                }
                else {
                    break;
                }
            }
            return selectedMovies;
        }
        else {
            throw new NoSuchFileException("No data file uploaded.");
        }
    }

}
