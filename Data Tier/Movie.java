/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

/**
 *
 * @author lctha
 */
public class Movie {
    //features
    private int movieID;
    private String title;
    private String director;
    private int yearReleased;
    private String description;
    private int genreID;
    
    //constructor
    
    public Movie()
    {
        
    }

    public Movie(int movieID, String title, String director, int yearReleased, String description, int genreID) {
        this.movieID = movieID;
        this.title = title;
        this.director = director;
        this.yearReleased = yearReleased;
        this.description = description;
        this.genreID = genreID;
    }
    
    public Movie(String title, String director, int yearReleased, String description, int genreID)
    {
        this.title = title;
        this.director = director;
        this.yearReleased = yearReleased;
        this.description = description;
        this.genreID = genreID;
    }
    
    //setters and getters

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }
    
}
