package edu.udayton.moivedb.retrofit;

/**
 * Created by xurengui on 06/07/2017.
 */

public class ListItem {
    private String movieName;
    private String movieImagePath;
    private String movieReview;
    private double movieRate;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieImagePath() {
        return movieImagePath;
    }

    public void setMovieImagePath(String movieImagePath) {
        this.movieImagePath = movieImagePath;
    }

    public String getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(String movieReview) {
        this.movieReview = movieReview;
    }

    public double getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(double movieRate) {
        this.movieRate = movieRate;
    }
}
