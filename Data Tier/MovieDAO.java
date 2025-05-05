/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movieapps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lctha
 */
public class MovieDAO {
    // getConnection
    // this code returns the Connection to an SQL database. Connections are needed
    // to send and receive data
    public Connection getConnection() {
        Connection conn = null;

        try {
            //load class with specific name SQLServer Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=MovieData;"
                    + "integratedSecurity=true;" + "encrypt=true;" + "trustServerCertificate=true;"
                    ;
            //use the driver to create a connection
            conn = DriverManager.getConnection(connectionUrl);
            return conn;
        } catch (Exception e) {
            System.out.println("---PLEASE ENSURE THE CONNECTION URL MATCHES YOUR LOCAL ENVIRONMENT---");
            e.printStackTrace();
        }

        return conn;
    }
        
    //returns a Movie instance with a specific ID
    public Movie getMovieById(int id)
    {
        //create variable to return to user
        Movie movie = new Movie();
        
        //establish a connection to the database
        Connection conn = getConnection();
        
        //create a resultset to store the data from our query
        ResultSet rs = null;
        try
        {
            //create an sql statement
            Statement stmt = conn.createStatement();
            
            //send an sql query to get the data we need and store the returned 
            //data in our result set
            String query = "SELECT MovieID, Title, Director, YearReleased, Description, GenreID FROM Movies WHERE MovieID=" + id;
            
            rs = stmt.executeQuery(query);
            
            //parse the data that comes back into our arraylist
            if(rs.next())
            {
                //create a Song object to store a single record       
                int movieID = Integer.parseInt(rs.getString(1));
                String title = rs.getString(2);
                String director = rs.getString(3);
                int yearReleased = Integer.parseInt(rs.getString(4));
                String description = rs.getString(5);
                int genreID = Integer.parseInt(rs.getString(6));
                
                movie = new Movie(movieID, title, director, yearReleased, description, genreID);
            }
            
            //close connection to server
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("There was a problem with the SQL when trying to get songs");
            sqle.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("There was a problem trying to get songs");
            e.printStackTrace();
        }
        
        //return songs to user
        return movie;
    }
    
    //accepts a song object, reads the id value, then updates the database
    //item for that song id with the other info from the passed in song
    public void updateMovieById(Movie movie)
    {
        //get connection to database
        Connection conn = getConnection();
        
        try
        {
            //make a statement
            Statement stmt = conn.createStatement();
            
            //create our update sql query -- just update the method to include ReleaseYear and GenreID
            String update = String.format("""
                                          UPDATE Movies SET Title = '%s',
                                          Director = '%s',
                                          YearReleased= %d,
                                          Description = '%s',
                                          GenreID = %d
                                          WHERE MovieID = %d""", movie.getTitle(), movie.getDirector(),
                                          movie.getYearReleased(), movie.getDescription(), movie.getGenreID(), movie.getMovieID());
            
            //send our update sql query by executeUpdate
            stmt.executeUpdate(update);
            
            //close connection to server
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("An issue occured with the SQL when trying to update a song");
            sqle.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("An issue occured when trying to update a song");
            e.printStackTrace();
        }
    }
    
    //this method takes int id as parameter and helps connect to the database to delete a movie which holds that id
    public void deleteMovieById(int id)
    {
        //establish connection
        Connection conn = getConnection();
        
        try
        {
            //create a statement
            Statement stmt = conn.createStatement();
            
            //write a sql delete statement
            String delete = "DELETE FROM Movies WHERE MovieID = "+id;
            
            //execute the statement
            stmt.executeUpdate(delete);
            
            //close the connection
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("There was an error with the SQL when deleting a movie");
            sqle.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("There was an error when deleting a movie");
            e.printStackTrace();
        }
    }
    
    
    //this method takes a Song instance as a parameter and proceed to connect to the database and insert this song to the database
    public void insertAMovie(Movie movie)
    {
        //establish a connection
        Connection conn = getConnection();
        
        try
        {
            //make a statement
            Statement stmt = conn.createStatement();
            
            //create our insert sql query
            String insert = String.format("""
                                          INSERT INTO Movies(title, director, yearReleased, description, genreID) VALUES('%s','%s', %d, '%s', %d)""", 
                                          movie.getTitle(), movie.getDirector(), movie.getYearReleased(), movie.getDescription(), movie.getGenreID());
            
            //send our update sql query
            stmt.executeUpdate(insert);
            
            //close connection to server
            conn.close();
        }
        catch(SQLException sqle)
        {
            System.out.println("An issue occured with the SQL when trying to insert a song");
            sqle.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("An issue occured when trying to insert a song");
            e.printStackTrace();
        }
    }
}
